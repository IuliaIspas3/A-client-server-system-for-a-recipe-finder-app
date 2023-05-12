package dao;

import model.Ingredient;

import java.sql.*;
import java.util.ArrayList;

public class IngredientDAOImplementation implements IngredientDAO
{
  private static IngredientDAOImplementation instance;

  private IngredientDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized IngredientDAOImplementation getInstance() throws SQLException
  {
    if (instance == null) {
      instance = new IngredientDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=recipedatabase", "postgres", "dupaxyz13");
  }

  @Override public void createIngredients(ArrayList<Ingredient> ingredients) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      for (int i = 0; i < ingredients.size(); i++)
      {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Ingredient (name) VALUES (?) ON CONFLICT DO NOTHING;");
        statement.setString(1, ingredients.get(i).getName());
        statement.executeUpdate();
      }
    }
  }

  @Override public ArrayList<Ingredient> readIngredients() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Ingredient;");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Ingredient> ingredients = new ArrayList<>();
      while (resultSet.next())
      {
        String name = resultSet.getString("name");
        Ingredient ingredient = new Ingredient(name);
        ingredients.add(ingredient);
      }
      return ingredients;
    }
  }
}
