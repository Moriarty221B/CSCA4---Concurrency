

// Trees define a canopy which covers a square area of the landscape
public class Tree{
	
private
	int xpos;	// x-coordinate of center of tree canopy
	int ypos;	// y-coorindate of center of tree canopy
	float ext;	// extent of canopy out in vertical and horizontal from center
	
	static float growfactor = 1000.0f; // divide average sun exposure by this amount to get growth in extent
	
public	
	Tree(int x, int y, float e){
		xpos=x; ypos=y; ext=e;
	}
	
	int getX() {
		return xpos;
	}
	
	int getY() {
		return ypos;
	}
	
	float getExt() {
		return ext;
	}
	
	void setExt(float e) {
		ext = e;
	}

	// Return the average sunlight for the cells covered by the tree.
	float sunexposure(Land land)
	{
		float exposure = 0.0f;
		for(int i = 0; i <=ext; i++)
		{
			for(int j = 0; j <=ext; j++)
			{
				try
				{
					exposure += land.getShade(xpos + i, ypos + j);
				}
				catch (Exception e){}
			}
		}

		return (float) (exposure / Math.pow((ext * 2) + 1, 2));
	}
	
	// is the tree extent within the provided range [minr, maxr)
	boolean inrange(float minr, float maxr)
	{
		return (ext >= minr && ext < maxr);
	}
	
	// Grow a tree according to its sun exposure.
	void sungrow(Land land)
	{
        // 1- Calculate the exposure for the tree.
        float sumExposure = sunexposure(land);

        // 2- Reduce sunlight for the cells.
        land.shadow(this);

        // 3- Grow the tree.
        ext += sumExposure / growfactor;
	}
}