package dao;

import model.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientDAO
{
  void createIngredients(ArrayList<Ingredient> ingredients) throws SQLException;
  ArrayList<Ingredient> readIngredients() throws SQLException;
}
