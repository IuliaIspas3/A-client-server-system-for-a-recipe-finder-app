package dao;

import model.Ingredient;
import model.Recipe;

import java.sql.*;
import java.util.ArrayList;

public class RecipeDAOImplementation implements RecipeDAO
{
  private static RecipeDAOImplementation instance;

  private RecipeDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RecipeDAOImplementation getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new RecipeDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://10.154.204.58:5432/postgres?currentSchema=recipedatabase",
        "postgres", "dupaxyz13");
  }

  @Override public void addRecipeToPerson(Recipe recipe, String username)
      throws SQLException
  {
    // not tested
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Recipe (username, title, description) VALUES  (?, ?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, username);
      statement.setString(2, recipe.getTitle());
      statement.setString(3, recipe.getDescription());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        int id = keys.getInt(1);
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients)
        {
          PreparedStatement statement1 = connection.prepareStatement(
              "INSERT INTO IngredientsInRecipe (recipeId, ingredient_name, amount, amount_type) VALUES (?, ?, ?, ?);");
          statement1.setInt(1, id);
          statement1.setString(2, ingredient.getName());
          statement1.setDouble(3, ingredient.getAmount());
          statement1.setString(4, ingredient.getAmountType());
          statement1.executeUpdate();
        }
      }
    }
  }

  @Override public void editPersonRecipe(Recipe recipe, String title,
      String description, ArrayList<Ingredient> ingredients) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE Recipe SET title = ?, description = ? WHERE recipeId = ?;");
      statement.setString(1, title);
      statement.setString(2, description);
      statement.setInt(3, recipe.getId());
      statement.executeUpdate();

      PreparedStatement statement1 = connection.prepareStatement(
          "DELETE FROM IngredientsInRecipe WHERE recipeId = ?;");
      statement1.setInt(1, recipe.getId());
      statement1.executeUpdate();

      for (Ingredient ingredient : ingredients)
      {
        PreparedStatement statement2 = connection.prepareStatement("INSERT INTO Ingredient (name) VALUES (?) ON CONFLICT DO NOTHING;");
        statement2.setString(1, ingredient.getName());
        statement2.executeUpdate();

        PreparedStatement statement3 = connection.prepareStatement(
            "INSERT INTO IngredientsInRecipe (recipeId, ingredient_name, amount, amount_type) VALUES (?, ?, ?, ?);");
        statement3.setInt(1, recipe.getId());
        statement3.setString(2, ingredient.getName());
        statement3.setDouble(3, ingredient.getAmount());
        statement3.setString(4, ingredient.getAmountType());
        statement3.executeUpdate();
      }
    }
  }

  @Override public void removeRecipe(Recipe recipe) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM Recipe WHERE recipeId = ?;");
      statement.setInt(1, recipe.getId());
      statement.executeUpdate();
    }
  }

  @Override public ArrayList<Recipe> readRecipes() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement1 = connection.prepareStatement(
          "SELECT * FROM Recipe;");
      ResultSet resultSet1 = statement1.executeQuery();
      ArrayList<Recipe> recipes = new ArrayList<>();
      while (resultSet1.next())
      {
        int id = resultSet1.getInt("recipeid");
        String username = resultSet1.getString("username");
        String title = resultSet1.getString("title");
        String description = resultSet1.getString("description");
        Recipe recipe = new Recipe(title, description, username);
        recipe.setId(id);
        recipe.setAllIngredients(getRecipeIngredients(connection, id));
        recipe.setAvrRating(getRating(connection, id));
        recipes.add(recipe);
      }
      return recipes;
    }
  }
  private double getRating(Connection connection,int id) throws SQLException{
    PreparedStatement statement = connection.prepareStatement("SELECT AVG(rate) FROM Rates WHERE recipeId = ?");
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next())
    {
      return resultSet.getDouble("avg");
    }
    return 0;
  }

  @Override public ArrayList<Recipe> readRecipesByUsername(String username)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Recipe WHERE username = ?;");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Recipe> recipes = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("recipeid");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        Recipe recipe = new Recipe(title, description, username);
        recipe.setId(id);
        recipe.setAllIngredients(getRecipeIngredients(connection, id));
        recipe.setAvrRating(getRating(connection, id));
        recipes.add(recipe);
      }
      return recipes;
    }
  }

  @Override public ArrayList<Recipe> readFavouriteRecipes(String username) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Recipe\n"
          + "JOIN Favourites on Recipe.recipeId = Favourites.recipeId\n"
          + "WHERE Favourites.username = ?;");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Recipe> recipes = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("recipeid");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        Recipe recipe = new Recipe(title, description, username);
        recipe.setId(id);
        recipe.setAllIngredients(getRecipeIngredients(connection, id));
        recipe.setAvrRating(getRating(connection, id));
        recipes.add(recipe);
      }
      return recipes;
    }
  }

  private ArrayList<Ingredient> getRecipeIngredients(Connection connection, int id) throws SQLException
  {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    PreparedStatement statement = connection.prepareStatement(
        "SELECT * FROM Ingredient\n"
            + "JOIN IngredientsInRecipe IIR on Ingredient.name = IIR.ingredient_name\n"
            + "WHERE recipeId = ?;");
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next())
    {
      String name = resultSet.getString("name");
      double amount = resultSet.getDouble("amount");
      String type = resultSet.getString("amount_type");
      Ingredient ingredient = new Ingredient(name);
      ingredient.setAmount(amount);
      ingredient.setAmountType(type);
      ingredients.add(ingredient);
    }
    return ingredients;
  }
}
