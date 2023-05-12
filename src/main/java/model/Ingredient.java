package model;

import java.io.Serializable;
import java.text.*;

public class Ingredient implements Serializable, IngredientInterface
{
  private final String name;
  private double amount;
  private String amountType;
  private final boolean select;
  private NumberFormat formatter;

  public Ingredient(String name)
  {
    this.name = name;
    this.select = false;
    this.amount = 0;
    this.amountType = "";
    this.formatter = new DecimalFormat("#0.00");
  }

  public void setAmount(double amount)
  {
    this.amount = Double.valueOf(formatter.format(amount));
  }

  public void setAmountType(String amountType)
  {
    this.amountType = amountType;
  }

  @Override public double getAmount()
  {
    return amount;
  }

  @Override public String getAmountType()
  {
    return amountType;
  }

  public String getName()
  {
    return name;
  }

  public Object getSelect()
  {
    return select;
  }

  public String toString()
  {
    return name;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Ingredient other = (Ingredient) obj;
    return this.name.equals(other.name);
  }
}
