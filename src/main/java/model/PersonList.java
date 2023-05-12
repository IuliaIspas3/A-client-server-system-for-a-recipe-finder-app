package model;

import java.util.ArrayList;

public class PersonList
{
  private static PersonList instance;
  private final ArrayList<Member> members;
  private final Administrator administrator;

  private PersonList()
  {
    this.members = new ArrayList<>();
    this.administrator = Administrator.getInstance();
  }

  public static synchronized PersonList getInstance()
  {
    if (instance == null)
    {
      instance = new PersonList();
    }
    return instance;
  }

  public void addMember(Member member)
  {
    this.members.add(member);
  }

  public void removeMember(Member member)
  {
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).equals(member))
        this.members.remove(member);
    }
  }

  public int size()
  {
    return members.size();
  }

  public ArrayList<Member> getMembers()
  {
    return members;
  }

  /*public void addRecipeToPerson(Recipe recipe, String username)
  {
    if (username.equals(Administrator.USERNAME))
    {
      this.administrator.addRecipe(recipe);
    }
    else
    {
      getMemberByUsername(username).addRecipe(recipe);
    }
  }

  public void editPersonRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients, String username)
  {
    if (username.equals(Administrator.USERNAME))
    {
      for (int i = 0; i < members.size(); i++)
      {
        for (int j = 0; j < members.get(i).getRecipes().size(); j++)
        {
          ArrayList<Recipe> temp = members.get(i).getRecipes();
          if (temp.get(j).equals(recipe))
          {
            members.get(i).editRecipe(temp.get(j), title, description, ingredients);
            return;
          }
        }
      }
      this.administrator.editRecipe(recipe, title, description, ingredients);
    }
    else
    {
      getMemberByUsername(username).editRecipe(recipe, title, description, ingredients);
    }
  }

  public void removeRecipeFromPerson(Recipe recipe, String username)
  {
    if (username.equals(Administrator.USERNAME))
    {
      for (int i = 0; i < members.size(); i++)
      {
        for (int j = 0; j < members.get(i).getRecipes().size(); j++)
        {
          ArrayList<Recipe> temp = members.get(i).getRecipes();
          if (temp.get(j).equals(recipe))
          {
            members.get(i).removeRecipe(temp.get(j));
            return;
          }
        }
      }
      this.administrator.removeRecipe(recipe);

    }
    else
    {
      getMemberByUsername(username).removeRecipe(recipe);
    }
  }

  public void addToFavourites(Recipe recipe, String username)
  {
    if (username.equals(Administrator.USERNAME))
    {
      throw new IllegalArgumentException("Administrator is not allowed to add a recipe to favourites.");
    }
    else
      getMemberByUsername(username).addFavourite(recipe);
  }

  public void removeFromFavourites(Recipe recipe, String username)
  {
    if (username.equals(Administrator.USERNAME))
    {
      throw new IllegalArgumentException("Administrator is not allowed to remove a recipe to favourites.");

    }
    else
      getMemberByUsername(username).removeFavourite(recipe);
  }

  private Member getMemberByUsername(String username)
  {
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).getUsername().equals(username))
        return members.get(i);
    }
    throw new NullPointerException("Person is not in the system.");
  }

  public boolean login(String username, String password)
  {
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).getUsername().equals(username) && members.get(i).getPassword().equals(password))
        return true;
    }
    if (administrator.getUsername().equals(username) && administrator.getPassword().equals(password))
      return true;
    return false;
  }

  public String toString()
  {
    String temp = "";
    for (int i = 0; i < members.size(); i++)
    {
      temp += members.get(i).toString1() + "\n\n";
    }
    return temp;
  }*/
}