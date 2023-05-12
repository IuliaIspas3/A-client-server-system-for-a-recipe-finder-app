package model;

import java.util.ArrayList;

public class RecipeList
{
  private static RecipeList instance;
  private final ArrayList<Recipe> recipes;

  private RecipeList()
  {
    this.recipes = new ArrayList<>();
  }

  public static synchronized RecipeList getInstance()
  {
    if (instance == null)
    {
      instance = new RecipeList();
    }
    return instance;
  }

  public void addRecipe(Recipe recipe)
  {
    this.recipes.add(recipe);
  }

  public void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients)
  {
    for (Recipe value : recipes)
    {
      if (value.equals(recipe))
      {
        value.setTitle(title);
        value.setDescription(description);
        value.setAllIngredients(ingredients);
      }
    }
  }

  public void removeRecipe(Recipe recipe)
  {
    for (int i = 0; i < recipes.size(); i++)
    {
      if (recipes.get(i).equals(recipe))
      {
        this.recipes.remove(recipes.get(i));
        return;
      }
    }
    throw new IllegalArgumentException("Recipe is not in the system.");
  }

  public ArrayList<Recipe> getRecipes()
  {
    return recipes;
  }

  public String toString()
  {
    String temp = "";
    for (Recipe recipe : recipes)
    {
      temp += recipe.toString1() + "\n\n";
    }
    return temp;
  }
}
