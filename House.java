/**
 * Specifies a house based on lot, square footage, age, and price.
 * Has methods to access these fields. Can also normalize data.
 * @author Vani Mohindra
 * @version May 30, 2017
 */
public class House 
{
	private double [] data;
	private double [] normalizedData;
	
	/**
	 * Constructs a new House with the given attibutes
	 * @param lot: the lot size of this House (in acres)
	 * @param sqft: the square footage of this House (in sq ft)
	 * @param age: the age of this House (in years)
	 */
	public House (double lot, double sqft, double age)
	{
		data = new double [4];
		data[0] = lot;
		data[1] = sqft;
		data[2] = age;
		data[3] = -1;
		normalizedData = null;
	}
	
	/**
	 * Constructs a new House with the given attributes
	 * @param lot: the lot size of this House (in acres)
	 * @param sqft: the square footage of this House (in sq ft)
	 * @param age: the age of this House (in years)
	 * @param price: the price of this House (in dollars)
	 */
	public House (double lot, double sqft, double age, double price)
	{
		data = new double [4];
		data[0] = lot;
		data[1] = sqft;
		data[2] = age;
		data[3] = price;
		normalizedData = null;
	}
	
	/**
	 * @return an array with this House's data in the order:
	 * lot size, square footage, and age
	 */
	public double [] getData ()
	{
		double [] mod = new double [3];
		for (int i=0; i<3; i++)
		{
			mod[i] = data[i];
		}
		return mod;
	}
	
	/**
	 * Returns the lot size of this House (in acres)
	 * @return the lot size of this House
	 */
	public double getLot ()
	{
		return data[0];
	}
	
	/**
	 * Returns the square footage of this House (in sq ft)
	 * @return the square footage of this House
	 */
	public double getSqft ()
	{
		return data[1];
	}
	
	/**
	 * Returns the age of this House (in years)
	 * @return the age footage of this House
	 */
	public double getAge ()
	{
		return data[2];
	}
	
	/**
	 * Returns the price of this House (in dollars)
	 * @return the price of this House
	 */
	public double getPrice ()
	{
		return data[3];
	}
	
	/**
	 * Normalize the input variables (age, lot, sqft) wrt to the trainig set data.
	 * 
	 * normalized value = (X - max(X))/(max(X) - min(X))
	 * @param maxAge
	 * @param minAge
	 * @param maxLot
	 * @param minLot
	 * @param maxSqft
	 * @param minSqft
	 */
	void normalize(double maxAge, double minAge
			, double maxLot, double minLot
			, double maxSqft, double minSqft)
	{
		normalizedData = new double[3];
		normalizedData[0] = (data[0] - maxLot)/(maxLot - minLot);
		normalizedData[1] = (data[1] - maxSqft)/(maxSqft - minSqft);
		normalizedData[2] = (data[2] - maxAge)/(maxAge - minAge);	
	}
	
	/**
	 * Returns the normalized lot size of this House (in acres)
	 * @return the lot size of this House
	 */
	public double getNormalizedLot ()
	{
		if (normalizedData == null)
			throw new IllegalArgumentException();
		
		return normalizedData[0];
	}
	
	/**
	 * Returns the square footage of this House (in sq ft)
	 * @return the square footage of this House
	 */
	public double getNormalizedSqft ()
	{
		if (normalizedData == null)
			throw new IllegalArgumentException();

		return normalizedData[1];
	}
	
	/**
	 * Returns the age of this House (in years)
	 * @return the age footage of this House
	 */
	public double getNormalizedAge ()
	{
		if (normalizedData == null)
			throw new IllegalArgumentException();

		return normalizedData[2];
	}
	
	public double [] getNormalizedVals ()
	{
		double [] norms = new double [4];
		norms[0] = 1;
		norms[1] = this.getLot();
		norms[2] = this.getAge();
		norms[3] = this.getSqft();
		return norms;
	}
}