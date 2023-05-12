package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Recipe implements Serializable, Cloneable
{
  private int id;
  private String title;
  private String description;
  private final ArrayList<Ingredient> ingredients;
  private final String username;
  private double avrRating;
  private NumberFormat formatter;
  private static final long SerialVersionUID = 1501;

  public Recipe(String title, String description, String username)
  {
    this.id = -1;
    this.title = title;
    this.description = description;
    this.ingredients = new ArrayList<>();
    this.username = username;
    this.avrRating = 0;
    this.formatter = new DecimalFormat("#0.00");
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setAllIngredients(ArrayList<Ingredient> ingredients)
  {
    this.ingredients.clear();
    this.ingredients.addAll(ingredients);
  }
  public void setAvrRating(double rating){
    this.avrRating = Double.valueOf(formatter.format(rating));
  }

  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public ArrayList<Ingredient> getIngredients()
  {
    return ingredients;
  }

  public void addAllIngredients(ArrayList<Ingredient> ingredients)
  {
    this.ingredients.addAll(ingredients);
  }

  public String getUsername()
  {
    return username;
  }

  public boolean ifRecipeContainsIngredients(ArrayList<Ingredient> ingredientsList)
  {
    return ingredients.containsAll(ingredientsList);
  }

  public String toString()
  {
    return title + " [rating: "+avrRating+"]";
  }

  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Recipe other = (Recipe) obj;

    if (this.ingredients.size() != other.ingredients.size())
      return false;

    for (int i = 0; i < ingredients.size(); i++)
    {
      if (!ingredients.get(i).equals(other.ingredients.get(i)))
        return false;
    }

    return this.title.equals(other.title) && this.description.equals(other.description) && this.username.equals(other.username);
  }

  @Override public Object clone() throws CloneNotSupportedException
  {
    return super.clone();
  }
}
