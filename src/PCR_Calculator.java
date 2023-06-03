import GeneralTools.MathTools;
/**
 * This class calculates the volumes of reagents needed for a PCR reaction
 * It is based on the following paper:
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4846334/
 * 
 * @author Miguel A. Reina
 */


 
public class PCR_Calculator {

    public final static String CODE_AMBIENTAL = "ambiental";
    public final static String CODE_LORENZ = "lorenz";
    public final static String CODE_THERMO_FISCHER = "thermo-fischer";
    /**
     * "Ambiental" protocol
     * DO NOT CHANGE
    */
    public final static double ambiental_final_buffer_X = 1;
    public final static double ambiental_final_MgCl2_mM = 2.5;
    public final static double ambiental_final_dNTP_mM = 0.2;
    public final static double ambiental_final_primer_mM = 0.3;
    public final static double ambiental_final_crudeDNA = 0.036;
    public final static double ambiental_final_enhancer_X = 1;
    public final static double ambiental_final_Taq_u = 0.06;

    public final static double[] ambiental_PCR_concentrations = {
        0,
        ambiental_final_buffer_X,
        ambiental_final_MgCl2_mM,
        ambiental_final_dNTP_mM,
        ambiental_final_primer_mM,
        ambiental_final_crudeDNA,
        ambiental_final_enhancer_X,
        ambiental_final_Taq_u
    };
    
   
    /**
     * Lorenz T. recommended concentrations
     * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4846334/
     * DO NOT CHANGE
     */
    public final static double lorenz_final_buffer_X = 1;
    public final static double lorenz_final_MgCl2_mM = 3;
    public final static double lorenz_final_dNTP_mM = 0.2;
    public final static double lorenz_final_primer_mM = 0.4;
    public final static double lorenz_final_crudeDNA = 0.036;
    public final static double lorenz_final_enhancer_X = 1;
    public final static double lorenz_final_Taq_u = 0.05;

    public final static double[] lorenz_PCR_concentrations = {
        0,
        lorenz_final_buffer_X,
        lorenz_final_MgCl2_mM,
        lorenz_final_dNTP_mM,
        lorenz_final_primer_mM,
        lorenz_final_crudeDNA,
        lorenz_final_enhancer_X,
        lorenz_final_Taq_u
    };

    /**
     * Thermo Fisher recommended concentrations
     */
    public final static double thermo_fischer_final_buffer_X = 1;
    public final static double thermo_fischer_final_MgCl2_mM = 1;// between 1 and 4
    public final static double thermo_fischer_final_dNTP_mM = 0.2; 
    public final static double thermo_fischer_final_primer_mM = 0.1;
    public final static double thermo_fischer_final_crudeDNA = 0.036;
    public final static double thermo_fischer_final_enhancer_X = 1;
    public final static double thermo_fischer_final_Taq_u = 0.025;
    
    public final static double[] thermo_fischer_PCR_concentrations = {
        0,
        thermo_fischer_final_buffer_X,
        thermo_fischer_final_MgCl2_mM,
        thermo_fischer_final_dNTP_mM,
        thermo_fischer_final_primer_mM,
        thermo_fischer_final_crudeDNA,
        thermo_fischer_final_enhancer_X,
        thermo_fischer_final_Taq_u
    };



    private int numReactions;
    private double reactionVolume;
    private double divisionVolume;
    private boolean withEnhancer;
    // 0 is water, 1 is buffer, 2 is MgCl2, 3 is dNTP, 4 is primers, 5 is DNA, 6 is enhancer, 7 is Taq
    private double[] final_concentrations=new double[8];
    // 0 is water, 1 is buffer, 2 is MgCl2, 3 is dNTP, 4 is primers, 5 is DNA, 6 is enhancer, 7 is Taq
    private double[] volumes;
    // 0 is water, 1 is buffer, 2 is MgCl2, 3 is dNTP, 4 is primers
    private double[] masterMix;
    
     /**
     * Initial Reagents concentrations
     * CHANGE AS NEEDED
     */
    private int safeVolumes = 0;

    private double initial_buffer_X = 10;
    private double initial_MgCl2_mM = 25;
    private double initial_dNTP_mM = 40;
    private double initial_primer_mM = 10;
    private double initial_enhancer_X = 10;
    private double initial_Taq_u = 5;
    private double[] initial_concentrations= 
        {
        0, // water
        initial_buffer_X,
        initial_MgCl2_mM,
        initial_dNTP_mM,
        initial_primer_mM,
        0, // DNA
        initial_enhancer_X,
        initial_Taq_u
        };
    






    /**
     * Constructor for PCR_Calculator
     * with no protocol specified, ambiental protocol is used
     * 
     * @param numReactions
     * @param reactionVolume
     * @param withEnhancer
     */
    public PCR_Calculator(int numReactions, Double reactionVolume, boolean withEnhancer)
    {   
        volumes = new double[9];
        masterMix = new double[6];
        this.numReactions=numReactions;
        this.reactionVolume=reactionVolume;
        this.withEnhancer=withEnhancer;
        assignProtocol(CODE_AMBIENTAL);
        calculateSinglePCRVolumes();
    }
    

    /**
     * Full constructor for PCR_Calculator
     * @param numReactions
     * @param reactionVolume
     * @param withEnhancer
     * @param protocol
     */
    public PCR_Calculator(int numReactions, Double reactionVolume, boolean withEnhancer, String protocol)
    {   
        volumes = new double[9];
        masterMix = new double[6];
        this.numReactions=numReactions;
        this.reactionVolume=reactionVolume;
        this.withEnhancer=withEnhancer;
        assignProtocol(protocol);
        calculateSinglePCRVolumes();
    }

    /**
     * Assigns the final concentrations to be used
     * @param protocol
     */
    private void assignProtocol(String protocol) {
        if(protocol.equals(CODE_AMBIENTAL))
        {
            final_concentrations=ambiental_PCR_concentrations;
        } else if(protocol.equals(CODE_LORENZ))
        {
            final_concentrations=lorenz_PCR_concentrations;
        } else {
            System.out.println("Protocol not recognized. Using ambiental protocol");
            final_concentrations=ambiental_PCR_concentrations;
        }
    }

    /**
     * Calculates the volumes of each reagent for a single PCR reaction
     * @return
     */
    private double[] calculateSinglePCRVolumes()
    {   
        double volBuffer = final_concentrations[1]*reactionVolume/initial_concentrations[1];
        volumes[1]=volBuffer;
        double volMg = final_concentrations[2]*reactionVolume/initial_concentrations[2];
        volumes[2]=volMg;
        double voldNTP = final_concentrations[3]*reactionVolume/initial_concentrations[3];
        volumes[3]=voldNTP;
        double volPrimers = final_concentrations[4]*reactionVolume/initial_concentrations[4];
        volumes[4]=volPrimers;
        volumes[5]=volPrimers;
        // 
        double volDNA = final_concentrations[5]*reactionVolume;
        volumes[6]=volDNA;

        if(withEnhancer)
        {
            double volEnhancer = final_concentrations[6]*reactionVolume/initial_concentrations[6];
            volumes[7]=volEnhancer;
        } 
        else {volumes[7]=0;}

        double volTaq = final_concentrations[7]*reactionVolume/initial_concentrations[7];
        volumes[8]=volTaq;

        double volWaterTypeI = reactionVolume;
        for(int i=1;i<volumes.length;i++)
        {
            volumes[i] = MathTools.roundTwoDecimals(volumes[i]);
            if(volumes[i] != 0) volWaterTypeI= volWaterTypeI - volumes[i];
            
        }
        volumes[0]=MathTools.roundTwoDecimals(volWaterTypeI);
        return volumes;
    }

    /**
     * Calculates the volumes of each reagent for the master mix
     * <pre> {@link} calculateSinglePCRVolumes() must be called first </pre>
     * @return
     */
    public double[] calculateMasterMixVolumes()
    {   
        double sum = 0;
        // goes until the last primer i=5
        for(int i=0;i<6;i++)
        {
            masterMix[i] = MathTools.roundTwoDecimals(volumes[i]*(numReactions+safeVolumes));
            sum += masterMix[i];
        }
        divisionVolume = sum/numReactions;

        
        return masterMix;
    }


    public double[] calculateAfterMasterMixVolumes()
    {
        double[] afterMasterMix = new double[3];
        afterMasterMix[0] = volumes[5];
        afterMasterMix[1] = volumes[6];
        afterMasterMix[2] = volumes[7];
        return afterMasterMix;
    }




    public double[] giveSinglePCRVolumes(){return volumes;}
    
    public double[] giveMasterMixVolumes(){return masterMix;}

    public double giveDivisionVolume(){return divisionVolume;}





}
