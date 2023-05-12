package model;

public interface IngredientInterface
{
  String getName();
  double getAmount();
  String getAmountType();
  Object getSelect();
  String toString();
  boolean equals(Object obj);
}
