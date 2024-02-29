import GeneralTools.MathTools;
class Cadmium_calculator
{
    public static void main(String[] args)
    {
        System.out.print(adicionar(15,2000));
    }

    public static double adicionar(double concentracion, double volumen_que_ya_hay){
        return MathTools.roundTwoDecimals(volumen_que_ya_hay*concentracion/(1000-concentracion));
    }
}
  