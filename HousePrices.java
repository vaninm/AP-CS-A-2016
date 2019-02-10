import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
/**
 * The HousePrice class uses House objects to generate a predictive model for house prices and predict
 * the selling price of a given home.
 * @author Vani Mohindra
 * @version June 5, 2017
 */
public class HousePrice
{
	private Set <House> houseprice;
	
	// Array of coefficients for Linear Regressions and constants that define what is stored in these coefficients.
	private double [] coeffs;
	private static final int CONSTANT = 0;
	private static final int LOT = 1;
	private static final int AGE = 2;
	private static final int SQFT = 3;
	
	// max and min values of the variables in the training data set.
	// These are used to normalize the training data set.	
	private double maxLot, maxAge, maxSqft, minLot, minAge, minSqft;
	
	/**
	 * Constructs a new HousePrice object that generates a predictive model for the given data
	 * @param houseprice: the set of houses with known selling prices to base predictive model on
	 */
	public HousePrice (Set <House> houseprice)
	{
		this.houseprice = houseprice;
		coeffs = new double [4];
		this.computeCoefficientsFromTrainingSet();
	}
	
	
	/**
	 * costDeltaFromActual measures the accuracy of the hypothesis (passed coefficient values) by using a cost function
	 * that is mean of the square of the differences between the predicted value by the passed in coefficients
	 * and the actual value.
	 * @param c coeficient array for computing the cost delta from actual
	 * @return how far this choice of coefficients is form the actual price.
	 */
	private double costDeltaFromActual(double[] c)
	{
		double delta = 0;
		Iterator<House> it = houseprice.iterator();
		while (it.hasNext())
		{
			House h = (House) it.next();
			
			double diff = (c[CONSTANT] + c[LOT]* h.getNormalizedLot() + c[AGE] * h.getNormalizedAge() + c[SQFT]* h.getNormalizedSqft() - h.getPrice());
			
			delta += (diff * diff);
			
		}
		return delta/(houseprice.size());
	}
	
	/**
	 * computes min and mix for the input vaiables to the cost function
	 * i.e. age, lot size and square footage.
	 */
	private void ComputeMinMaxForInputVarianbles(){
		
		minAge = Double.MAX_VALUE;
		minLot = Double.MAX_VALUE;
		minSqft = Double.MAX_VALUE;
		
		Iterator<House> it = houseprice.iterator();
		while (it.hasNext())
		{
			House h = (House) it.next();
			
			if (h.getAge() > maxAge)
				maxAge = h.getAge();
			if (h.getAge() < minAge)
				minAge = h.getAge();

			if (h.getLot() > maxLot)
				maxLot = h.getLot();
			if (h.getLot() < minLot)
				minLot = h.getLot();

			if (h.getSqft() > maxSqft)
				maxSqft = h.getSqft();
			if (h.getSqft() < minSqft)
				minSqft = h.getSqft();					
					
		}
		
	}
	
	/**
	 * Normalize the input variables (age, lot, sqft) wrt to the trainig set data.
	 * normalized value = (X - max(X))/(max(X) - min(X))
	 */
	private void normalizeInput()
	{	
		ComputeMinMaxForInputVarianbles();
		
		Iterator<House> it = houseprice.iterator();
		while (it.hasNext())
		{
			House h = (House) it.next();
			
			h.normalize(maxAge,minAge, maxLot, minLot, maxSqft, minSqft);
							
		}	
	}	
	
	/**
	 * getSlope returns the slope at the current point for all the variables (constant, age, sft, lot).
	 * @return the slopes of the cost function at the given constant, age, sqft, and lot, in that order
	 */
	private double[] getSlope()
	{	
		double[] slope = new double[4];
		
		Iterator<House> it = houseprice.iterator();
		while (it.hasNext())
		{
			House h = (House) it.next();
			
			double delta = coeffs[CONSTANT] + coeffs[LOT]* h.getNormalizedLot() + coeffs[AGE] * h.getNormalizedAge() + coeffs[SQFT]* h.getNormalizedSqft() - h.getPrice();
			
			slope[CONSTANT] += delta;
			slope[AGE] += (delta * h.getNormalizedAge());
			slope[SQFT] += (delta * h.getNormalizedSqft());
			slope[LOT] += (delta * h.getNormalizedLot());
			
		}
		
		for (double s : slope){
			s = s / houseprice.size();
		}
	
		return slope;			
	}
	
	/**
	 * Prints the contents of the given coefficient array 
	 * @param c: array whose contents are to be printed
	 */
	public void printCoefficients(double [] c)
	{
		System.out.println("Printing Coefficient");
		System.out.println(" Constant " + c[CONSTANT]);
		System.out.println(" Age " + c[AGE]);
		System.out.println(" Lot " + c[LOT]);
		System.out.println(" SqFt " + c[SQFT]);	
	}
	
	/**
	 * computes the coefficients of the variables (age, lot, sq feet) and the constant value for the line
	 * that best represents the training data set that is provided. 
	 */
	public void computeCoefficientsFromTrainingSet ()
	{
		// number of iterations with the same step size before we increase the step size.
		final int maxSameStepSizeIteration = 10;
		
		// initiatlize the initial coefficient vector to zero.
		coeffs[CONSTANT] = 0;
		coeffs[LOT] = 0;
		coeffs[AGE] = 0; 
		coeffs[SQFT] =0;
		
		// initial gradient descent step size
		double stepSize = 1.0;
		
		// normalize the input data set to reduce the number of iterations required to compute the linear equation.
		normalizeInput();
		
		double currentCostFunctionValue = Double.MAX_VALUE;
		double costFuctionValueAtNewPoint;
		
		// compute coefficiente using gradient descent
		double[] nextCoeffs = coeffs.clone();	
		int noChangeCount = 0;
		
		while (true)
		{	
			// compute the slope for all the variables.
			double[] slope = getSlope(); 
				
			// determine the step size that will lower the cost function for the given slope.
			
			while (true)
			{
				//Compute new Coefficients using the slope and the step size.
				for (int i=0; i< coeffs.length; i++)
				{
					nextCoeffs[i] = coeffs[i] - stepSize * slope[i];
				}
				
				costFuctionValueAtNewPoint = costDeltaFromActual(nextCoeffs);
				
				if (costFuctionValueAtNewPoint <= currentCostFunctionValue)				   
				   break;
				
				// decrease the stepSize
				stepSize = stepSize/10;
				   
				noChangeCount = 0;
			}
			
			// if required please increase the step size to descent faster.
			noChangeCount++;
			if (noChangeCount > maxSameStepSizeIteration)
			{
				stepSize = stepSize*10;
				noChangeCount = 0;
			}
			
			// new point is closer to minima. Move to this point.			
			for (int i=0; i< coeffs.length; i++)
			{
				coeffs[i] = nextCoeffs[i];		
			}
		
			// Convergence check				  		   
			if (Math.abs(costFuctionValueAtNewPoint - currentCostFunctionValue) < 1.0)
			{						
				break;
			}
			
			// update the cost function value
			currentCostFunctionValue = costFuctionValueAtNewPoint;
		}			
	}
	
	/**
	 * returns the predicted sale price of the house.
	 * 
	 * @param h: house whose variables are set
	 * @return the predicted sale price of the house given by the current coefficients 
	 */

	public double predictPrice (House h)
	{
		//normalize the house data.
		h.normalize(maxAge,minAge, maxLot, minLot, maxSqft, minSqft);
		return coeffs[CONSTANT] + coeffs[LOT]* h.getNormalizedLot() + coeffs[AGE] * h.getNormalizedAge() + coeffs[SQFT]* h.getNormalizedSqft();			
	}

	/**
	 * print the linear equation that appromixates the training data set.
	 * 
	 */
	public void printEquation ()
	{
		System.out.println("Linear equation that models the provided training data set.");
		
		System.out.print((int)coeffs[CONSTANT]);
		System.out.print(" + (" + (int)coeffs[AGE] + " *  ( AGE - " + maxAge + " ) / " + (maxAge - minAge) + " ) ");
		System.out.print(" + (" + (int)coeffs[LOT] + " *  ( LOT - " + maxLot + " ) / " + (maxLot - minLot) + " ) ");
		System.out.print(" + (" + (int)coeffs[SQFT] + " *  ( SqFt - " + maxSqft + " ) / " + (maxSqft - minSqft) + " ) ");
		System.out.println();
		
		System.out.println("Note: Input variables were normalized and hence the normalization function is present in the equation.");
					
	}

	public static void main (String [] args)
	{
		HashSet <House> properties = new HashSet<House>();
		
		House orbit = new House (1.13, 3000, 45, 3388000);
		House blueHills = new House (0.41, 2091, 62, 1999000);
		House arcadia = new House (1, 3042, 45, 2825000);
		House greenbrook = new House (0.5, 2543, 61, 1998000);
		House burgundy = new House (0.92, 3972, 43, 3125000);
		House vanderbilt = new House (0.19, 1200, 57, 1610000);
		House tricia = new House (0.41, 2949, 33, 2600000);
		House mabel = new House (0.3, 2527, 55, 2622888);
		House viewOak = new House (0.3, 3162, 50, 2570000);
		House titus = new House (0.32, 2988, 55, 2622888);
		House saratogaCreek = new House (0.26, 2487, 57, 2300000);		
		House harleigh= new House (0.43, 3497, 49, 2845000);	
		House karn = new House (0.3, 2860, 47, 2470000);		
		
		properties.add(orbit);
		properties.add(blueHills);
		properties.add(arcadia);
		properties.add(greenbrook);
		properties.add(burgundy);
		properties.add(vanderbilt);
		properties.add(tricia);
		properties.add(mabel);
		properties.add(viewOak);
		properties.add(titus);		
		properties.add(saratogaCreek);
		properties.add(harleigh);
		properties.add(karn);
		
		HousePrice realtor = new HousePrice(properties);
				
		realtor.printEquation();
		System.out.println(" titus " + realtor.predictPrice(titus));
		System.out.println(" arcadia " + realtor.predictPrice(arcadia));
		System.out.println(" viewOak " + realtor.predictPrice(viewOak));
		System.out.println(" karn " + realtor.predictPrice(karn));
		System.out.println(" harleigh " + realtor.predictPrice(harleigh) );		
		
	}
}