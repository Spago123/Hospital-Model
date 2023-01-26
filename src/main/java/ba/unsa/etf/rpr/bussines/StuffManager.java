package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.ArrayList;
import java.util.List;

/**
 * Stuff manger is a manager class which task is to verify different things
 */
public class StuffManager {
    /**
     * Method that verifies if the password is valid
     * @param pass
     * @return boolean
     */
    public static boolean verifyPassword(String pass){
        if(pass == null || pass.length()<7 || pass.length()>15){
            return false;
        }
        return true;
    }

    private static boolean checkDate(int d, int m, int g){
        int[] numberOfDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(g % 4 == 0 && g % 100 != 0 || g % 400 == 0) numberOfDays[1]++;
        if(g < 1 || d < 1 || m < 1 || m > 12 || d > numberOfDays[m - 1])
            return false;

        return true;
    }

    private static int[] giveNumbers(String UIN){
        int[] numbers = new int[13];
        for(int i = 0;i < UIN.length(); i++){
            numbers[i]=(Integer.parseInt(String.valueOf(UIN.charAt(i))));
        }
        return numbers;
    }

    private static int calculateLast(int[] niz) throws HospitalException {
        int last = 11-(7*(niz[0]+niz[6])+6*(niz[1]+niz[7])+5*(niz[2]+niz[8])+4*(niz[3]+niz[9])+3*(niz[4]+niz[10])+2*(niz[5]+niz[11]))%11;
        if(last == 11) last = 0;
        else if(last == 10) throw new HospitalException("UIN is not valid");
        return last;
    }

    public static boolean checkUIN(String UIN) throws HospitalException {
        Long uin;
        try{
            uin = Long.parseLong(UIN);
        } catch (NumberFormatException e) {
            return false;
        }
        if(uin<0)return false;
        if(UIN.length()!=13)return false;
        int[] numbers = giveNumbers(UIN);
        if(numbers[12]!=calculateLast(numbers)) return false;
        if(!checkDate(numbers[0]*10+numbers[1],numbers[2]*10+numbers[3],numbers[4]*100+numbers[5]*10+numbers[6])) return false;
        return true;
    }
// = 11−(7∙ ( + )+6∙ ( + )+5∙ ( + )+4∙ ( + )+3∙ ( + )+2∙ ( + ))%11
/*
bool GradjaninBiH::ProvjeriJMBG(long long int jmbg){
    DajParametre(jmbg);
    if(niz[12]!=IzracunajZadnji())throw std::logic_error("JMBG nije validan");
    if(!ProvjeriDatum(niz[0]*10+niz[1],niz[2]*10+niz[3],niz[4]*100+niz[5]*10+niz[6]))return false;
    return true;
}

int GradjaninBiH::IzracunajZadnji(){
    int zadnji=11-(7*(niz[0]+niz[6])+6*(niz[1]+niz[7])+5*(niz[2]+niz[8])+4*(niz[3]+niz[9])+3*(niz[4]+niz[10])+2*(niz[5]+niz[11]))%11;
    if(zadnji==11)zadnji=0;
    else if(zadnji==10)throw std::logic_error("JMBG nije validan");
    return zadnji;
}
void GradjaninBiH::DajParametre(long long int jmbg){
    int brojac=0;
    do{
        niz[12-brojac]=jmbg%10;jmbg/=10;brojac++;
    }while(jmbg!=0);
}



     */

}
