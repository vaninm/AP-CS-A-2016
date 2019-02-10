/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

/**
 */
import java.util.ArrayList;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class BoundedGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }

    /**
     * Returns the number of rows in this BoundedGrid
     * @return the number of rows in this BoundedGrid
     */
    public int getNumRows()
    {
        return occupantArray.length;
    }
    
    /**
     * Returns the number of columns in this BoundedGrid
     * @return the number of columns in this BoundedGrid
     */
    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

    /**
     * Determines if the specified location is within the bounds of this grid
     * 
     * @param the location to be checked
     * @return true if the given location is within the bounds of this grid; false otherwise
     */
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    /**
     * Returns all occupied locations in this BoundedGrid
     * @return an ArrayList of all occupied locations in this BoundedGrid
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    /**
     * Returns the object at the specified location in this BoundedGrid
     * 
     * @exception IllegalArgumentException is the specified location is not contained in this BoundedGrid
     * @param the location being searched
     * @return the object at the specified location in this BoundedGrid
     */
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    /**
     * Places the given object at location loc in this BoundedGrid and returns
     * the object previously stored at that location
     * 
     * @param loc: the location in the grid where obj is to be inserted
     * @param obj: the object to be inserted at location loc in tis BoundedGrid
     * @return the object previously stored at location loc in this BoundedGrid
     * @exception IllegalArgumentException if the location is not contained within this BoundedGrid
     * @exception NullPointerException if the object to be placed in this BoundedGrid is null
     */
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * Removes and returns the item at the specified location in this BoundedGrid
     * 
     * @param loc: the location of the object to be removed in this BoundedGrid 
     * @return the item removed from the given location in this BoundedGrid
     * @exception IllegalArgumentException if the given location is not contained within this BoundedGrid
     */
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}