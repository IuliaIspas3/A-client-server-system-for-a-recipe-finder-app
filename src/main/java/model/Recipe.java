package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable
{
  private int id;
  private String title;
  private String description;
  private final ArrayList<Ingredient> ingredients;
  private final String username;
  private int sum;
  private int numberOfRatings;

  public Recipe(String title, String description, String username)
  {
    this.id = -1;
    this.title = title;
    this.description = description;
    this.ingredients = new ArrayList<>();
    this.username = username;
    this.sum = 0;
    this.numberOfRatings = 0;
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

  public void addIngredient(Ingredient ingredient)
  {
    this.ingredients.add(ingredient);
  }

  public void addAllIngredients(ArrayList<Ingredient> ingredients)
  {
    this.ingredients.addAll(ingredients);
  }

  public String getUsername()
  {
    return username;
  }

  public void removeIngredient(Ingredient ingredient)
  {
    for (int i = 0; i < ingredients.size(); i++)
    {
      if (ingredients.get(i).equals(ingredient))
      {
        ingredients.remove(ingredients.get(i));
      }
    }
  }

  public void addRating(int rate)
  {
    this.sum += rate;
    this.numberOfRatings++;
  }

  public double getRating()
  {
    return (double) this.sum / this.numberOfRatings;
  }

  public boolean ifRecipeContainsIngredients(ArrayList<Ingredient> ingredientsList)
  {
    return ingredients.containsAll(ingredientsList);
  }

  public String toString()
  {
    return title;
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

  public String toString1()
  {
    String temp = title + "\n" + description + "\n" + username + "\ningredients:";
    for (int i = 0; i < ingredients.size(); i++)
    {
      temp += "\n" + ingredients.get(i);
    }
    return temp;
  }
}
