package model;

import java.util.ArrayList;

public class IngredientList
{
  private static IngredientList instance;
  private ArrayList<Ingredient> ingredients;

  private IngredientList()
  {
    this.ingredients = new ArrayList<>();
  }

  public static synchronized IngredientList getInstance()
  {
    if (instance == null)
    {
      instance = new IngredientList();
    }
    return instance;
  }

  public void addIngredient(Ingredient ingredient)
  {
    if (!exists(ingredient))
      ingredients.add(ingredient);
  }

  public void addAllIngredients(ArrayList<Ingredient> ingredients)
  {
    for (int i = 0; i < ingredients.size(); i++)
    {
      addIngredient(ingredients.get(i));
    }
  }

  public ArrayList<Ingredient> getIngredients()
  {
    return ingredients;
  }

  private boolean exists(Ingredient ingredient)
  {
    for (int i = 0; i < ingredients.size(); i++)
    {
      if (ingredients.get(i).equals(ingredient))
      {
        return true;
      }
    }
    return false;
  }
}
