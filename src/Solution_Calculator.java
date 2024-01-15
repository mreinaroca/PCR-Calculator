public class Solution_Calculator {
    
    private Double initial_concentration;
    private Double final_concentration;
    private Double final_volume;
    private Double initial_volume=0.0;
    private Double volume_solvent_add=0.0;
    public Solution_Calculator(Double initial_concentration, Double final_concentration, Double final_volume)
    {
        this.initial_concentration=initial_concentration;
        this.final_concentration=final_concentration;
        this.final_volume=final_volume;
    }

        public Solution_Calculator(double initial_concentration, double final_concentration, double final_volume)
        {
            this.initial_concentration = Double.valueOf(initial_concentration);
            this.final_concentration = Double.valueOf(final_concentration);
            this.final_volume = Double.valueOf(final_volume);
        }

        public static void main(String[] args)
        {
            Solution_Calculator sc = new Solution_Calculator(50, 1, 1000);
            System.out.print(sc.console_print());
        }

    public Double find_initial_volume()
    {
        if(initial_volume==0.0)
        {
            initial_volume = final_volume*final_concentration/initial_concentration;
        }
        return initial_volume;
    }

    public Double find_volume_solvent_add()
    {
        
        if(volume_solvent_add==0.0)
        {
            find_initial_volume();
            volume_solvent_add = final_volume-initial_volume;
        }

        return volume_solvent_add;
    }

    public String console_print()
    {   
        find_initial_volume();
        find_volume_solvent_add();
        String output = "\nFor a final "+ final_concentration + " of "+ final_volume + " add " + initial_volume + " of solute plus " + volume_solvent_add + " of solvent\n";
        return output;
        
    }


}
