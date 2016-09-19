package data;

/**
 *
 * @author tgorka
 */
public class ChefNain extends Nain {
    private static int COEFF_DEFENSE = 2;
    private final int COUT_DE_BASE   = 3;
    
    public ChefNain() {
        super();
    }
    
    @Override
    public int getDefense() {
        return super.getDefense() * COEFF_DEFENSE;
    }
    
    @Override
    public int getCOUT_DE_BASE() {
        return COUT_DE_BASE;
    }
}
