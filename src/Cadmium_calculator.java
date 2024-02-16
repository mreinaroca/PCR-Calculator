import GeneralTools.MathTools;
class Cadmium_calculator
{
    public static void main(String[] args)
    {
        System.out.print(adicionar(115));
    }

    public static double adicionar(double concentracion){
        return MathTools.roundTwoDecimals(200*concentracion/(1000-concentracion));
    }
}
  