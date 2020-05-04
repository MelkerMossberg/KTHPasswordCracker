import javax.swing.plaf.synth.SynthScrollBarUI;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PasswordCrack {
    private static ArrayList<UserEntry> unsolvedUsers = new ArrayList();
    private static ArrayList<String> dictionary = new ArrayList<>();
    private static List<Function> namePermutations = new ArrayList<>();
    private static List<Function> manglePermutations = new ArrayList<>();
    private static List<Function> charPermutations = new ArrayList<>();

    public static void main(String[] args){
        System.out.println(jcrypt.crypt("Pk", "12345678"));
        loadDictionary(args[0]);
        loadUserInfo(args[1]);
        addNamePermutationFunctions();
        addManglePermutationFunctions();
        addCharPermutationFunctions();
        System.out.println("START LENGTH: " + unsolvedUsers.size());

        tryMostCommon();
        tryNameVariants();
        tryDictionaryVariants();
        System.out.println("FINAL LENGTH: " + unsolvedUsers.size());
    }

    private static void tryMostCommon() {
        String[] mostCommon = ("123456,12345,123456789,password,iloveyou,princess,1234567,rockyou,12345678,abc123,nicole,daniel,babygirl,monkey,lovely,jessica,654321,michael,ashley,qwerty,111111,iloveu,000000,michelle,tigger,sunshine,chocolate,password1,soccer,anthony,friends,butterfly,purple,angel,jordan,liverpool,justin,loveme,fuckyou,123123,football,secret,andrea,carlos,jennifer,joshua,bubbles,1234567890,superman,hannah,amanda,loveyou,pretty,basketball,andrew,angels,tweety,flower,playboy,hello,elizabeth,hottie,tinkerbell,charlie,samantha,barbie,chelsea,lovers,teamo,jasmine,brandon,666666,shadow,melissa,eminem,matthew,robert,danielle,forever,family,jonathan,987654321,computer,whatever,dragon,vanessa,cookie,naruto,summer,sweety,spongebob,joseph,junior,softball,taylor,yellow,daniela,lauren,mickey,princesa,alexandra,alexis,jesus,estrella,miguel,william,thomas,beautiful,mylove,angela,poohbear,patrick,iloveme,sakura,adrian,alexander,destiny,christian,121212,sayang,america,dancer,monica,richard,112233,princess1,555555,diamond,carolina,steven,rangers,louise,orange,789456,999999,shorty,11111,nathan,snoopy,gabriel,hunter,cherry,killer,sandra,alejandro,buster,george,brittany,alejandra,patricia,rachel,tequiero,7777777,cheese,159753,arsenal,dolphin,antonio,heather,david,ginger,stephanie,peanut,blink182,sweetie,222222,beauty,987654,victoria,honey,00000,fernando,pokemon,maggie,corazon,chicken,pepper,cristina,rainbow,kisses,manuel,myspace,rebelde,angel1,ricardo,babygurl,heaven,55555,baseball,martin,greenday,november,alyssa,madison,mother,123321,123abc,mahalkita,batman,september,december,morgan,mariposa,maria,gabriela,iloveyou2,bailey,jeremy,pamela,kimberly,gemini,shannon,pictures,asshole,sophie,jessie,hellokitty,claudia,babygirl1,angelica,austin,mahalko,victor,horses,tiffany,mariana,eduardo,andres,courtney,booboo,kissme,harley,ronaldo,iloveyou1,precious,october,inuyasha,peaches,veronica,chris,888888,adriana,cutie,james,banana,prince,friend,jesus1,crystal,celtic,zxcvbnm,edward,oliver,diana,samsung,freedom,angelo,kenneth,master,scooby,carmen,456789,sebastian,rebecca,jackie,spiderman,christopher,karina,johnny,hotmail,0123456789,school,barcelona,august,orlando,samuel,cameron,slipknot,cutiepie,monkey1,50cent,bonita,kevin,bitch,maganda,babyboy,casper,brenda,adidas,kitten,karen,mustang,isabel,natalie,cuteako,javier,789456123,123654,sarah,bowwow,portugal,laura,777777,marvin,denise,tigers,volleyball,jasper,rockstar,january,fuckoff,alicia,nicholas,flowers,cristian,tintin,bianca,chrisbrown,chester,101010,smokey,silver,internet,sweet,strawberry,garfield,dennis,panget,francis,cassie,benfica,love123,696969,asdfgh,lollipop,olivia,cancer,camila,qwertyuiop,superstar,harrypotter,ihateyou,charles,monique,midnight,vincent,christine,apples,scorpio,jordan23,lorena,andreea,mercedes,katherine,charmed,abigail,rafael,icecream,mexico,brianna,nirvana,aaliyah,pookie,johncena,lovelove,fucker,abcdef,benjamin,131313,gangsta,brooke,333333,hiphop,aaaaaa,mybaby,sergio,welcome,metallica,julian,travis,myspace1,babyblue,sabrina,michael1,jeffrey,stephen,love,dakota,catherine,badboy,fernanda,westlife,blondie,sasuke,smiley,jackson,simple,melanie,steaua,dolphins,roberto,fluffy,teresa,piglet,ronald,slideshow,asdfghjkl,minnie,newyork,jason,raymond,santiago,jayson,88888888,5201314,jerome,gandako,muffin,gatita,babyko,246810,sweetheart,chivas,ladybug,kitty,popcorn,alberto,valeria,cookies,leslie,jenny,nicole1,12345678910,leonardo,jayjay,liliana,dexter,sexygirl,232323,amores,rockon,christ,babydoll,anthony1,marcus,bitch1,fatima,miamor,lover,chris1,single,eeyore,lalala,252525,scooter,natasha,skittles,brooklyn,colombia,159357,teddybear,winnie,happy,manutd,123456a,britney,katrina,christina,pasaway,cocacola,mahal,grace,linda,albert,tatiana,london,cantik,0123456,lakers,marie,teiubesc,147258369,charlotte,natalia,francisco,amorcito,smile,paola,angelito,manchester,hahaha,elephant,mommy1,shelby,147258,kelsey,genesis,amigos,snickers,xavier,turtle,marlon,linkinpark,claire,stupid,147852,marina,garcia,fuckyou1,diego,brandy,letmein,hockey,444444,sharon,bonnie,spider,iverson,andrei,justine,frankie,pimpin,disney,rabbit,54321,fashion,soccer1,red123,bestfriend,england,hermosa,456123,qazwsx,bandit,danny,allison,emily,102030,lucky1,sporting,miranda,dallas,hearts,camille,wilson,potter,pumpkin,iloveu2,number1,katie,guitar,212121,truelove,jayden,savannah,hottie1,phoenix,monster,player,ganda,people,scotland,nelson,jasmin,timothy,onelove,ilovehim,shakira,estrellita,bubble,smiles,brandon1,sparky,barney,sweets,parola,evelyn,familia,love12,nikki,motorola,florida,omarion,monkeys,loverboy,elijah,joanna,canada,ronnie,mamita,emmanuel,thunder,999999999,broken,rodrigo,maryjane,westside,california,lucky,mauricio,yankees,jackass,jamaica,justin1,amigas,preciosa,shopping,flores,mariah,matrix,isabella,tennis,trinity,jorge,sunflower,kathleen,bradley,cupcake,hector,martinez,elaine,robbie,friendster,cheche,gracie,connor,hello1,valentina,melody,darling,sammy,jamie,santos,abcdefg,joanne,candy,fuckyou2,loser,dominic,pebbles,sunshine1,swimming,millie,loving,gangster,blessed,compaq,taurus,gloria,tyler,aaron,darkangel,kitkat,megan,dreams,sweetpea,bettyboop,jessica1,cynthia,cheyenne,ferrari,dustin,iubire,a123456,snowball,purple1,violet,darren,starwars,bestfriends,inlove,kelly,batista,karla,sophia,chacha,biteme,marian,sydney,sexyme,pogiako,gerald,jordan1,010203,daddy1,zachary,daddysgirl,billabong,carebear,froggy,pinky,erika,oscar,skater,raiders,nenita,tigger1,ashley1,charlie1,gatito,lokita,maldita,buttercup,nichole,bambam,nothing,glitter,bella,amber,apple,123789,sister,zacefron,tokiohotel,loveya,lindsey,money,lovebug,bubblegum,marissa,dreamer,darkness,cecilia,lollypop,nicolas,google,lindsay,cooper,passion,kristine,green,puppies,ariana,fuckme,chubby,raquel,lonely,anderson,sammie,sexybitch,mario,butter,willow,roxana,mememe,caroline,susana,kristen,baller,hotstuff,carter,stacey,babylove,angelina,miller,scorpion,sierra,playgirl,sweet16,012345,rocker,bhebhe,gustavo,marcos,chance,123qwe,kayla,james1,football1,eagles,loveme1,milagros,stella,lilmama,beyonce,lovely1,rocky,daddy,catdog,armando,margarita,151515,loves,lolita,202020,gerard,undertaker,amistad,williams,qwerty1,freddy,capricorn,caitlin,bryan,delfin,dance,cheerleader,password2,PASSWORD,martha,lizzie,georgia,matthew1,enrique,zxcvbn,badgirl,andrew1,141414,11111111,dancing,cuteme,booger,amelia,vampire,skyline,chiquita,angeles,scoobydoo,janine,tamara,carlitos,money1,sheila,justme,ireland,kittycat,hotdog,yamaha,tristan,harvey,israel,legolas,michelle1,maddie,angie,cinderella,jesuschrist,lester,ashton,ilovejesus,tazmania,remember,xxxxxx,tekiero,thebest,princesita,lucky7,jesucristo,peewee,paloma,buddy1,deedee,miriam,april,patches,regina,janice,cowboys,myself,lipgloss,jazmin,rosita,happy1,felipe,chichi,pangit,mierda,genius,741852963,hernandez,awesome,walter,tinker,arturo,silvia,melvin,celeste,pussycat,gorgeous,david1,molly,honeyko,mylife,animal,penguin,babyboo,loveu,simpsons,lupita,boomer,panthers,hollywood,alfredo,musica,johnson,ilovegod,hawaii,sparkle,kristina,sexymama,crazy,valerie,spencer,scarface,hardcore,098765,00000000,winter,hailey,trixie,hayden,micheal,wesley,242424,0987654321,marisol,nikita,daisy,jeremiah,pineapple,mhine,isaiah,christmas,cesar,lolipop,butterfly1,chloe,lawrence,xbox360,sheena,murphy,madalina,anamaria,gateway,debbie,yourmom,blonde,jasmine1,please,bubbles1,jimmy,beatriz,poopoo,diamonds,whitney,friendship,sweetness,pauline,desiree,trouble,741852,united,marley,brian,barbara,hannah1,bananas,julius,leanne,sandy,marie1,anita,lover1,chicago,twinkle,pantera,february,birthday,shadow1,qwert,bebita,87654321,twilight,imissyou,pollito,ashlee,tucker,cookie1,shelly,catalina,147852369,beckham,simone,nursing,iloveyou!,eugene,torres,damian,123123123,joshua1,bobby,babyface,andre,donald,daniel1,panther,dinamo,mommy,juliana,cassandra").split(",");
        for(int i = 0; i < unsolvedUsers.size(); i++){
            UserEntry user = unsolvedUsers.get(i);
            for (String s : mostCommon) {
                String crypto = jcrypt.crypt(user.salt, s);
                if (crypto.equals(user.password)) {
                    System.out.println("'" + s + "' is pass for " + user.firstName + " " + user.surName);
                    unsolvedUsers.remove(i);
                }
            }
        }
        System.out.println("MOST COMMON DONE. Left: " + unsolvedUsers.size());
    }

    private static void tryNameVariants() {
        boolean userIsSolved;
        for (int i = 0; i < unsolvedUsers.size(); i++){
            userIsSolved = false;
            UserEntry user = unsolvedUsers.get(i);
            ArrayList<String> userNamePermutations = new ArrayList();
            ArrayList<String> userManglePermutations = new ArrayList();
            String[] userName = new String[]{user.firstName, user.surName};
            int finalUserRef = i;
            for (Function func : namePermutations) {
                String namePermutation = (String) func.apply(userName);
                userNamePermutations.add(namePermutation);
                String crypto = jcrypt.crypt(user.salt, namePermutation);
                if (crypto.equals(user.password)) {
                    System.out.println("'" + namePermutation + "' is pass for " + user.firstName + " " + user.surName);
                    unsolvedUsers.remove(finalUserRef);
                    userIsSolved = true;
                    break;
                }
            }
            if (!userIsSolved){
                for (Object usersPermutation : userNamePermutations) {
                    String namePermutation = (String) usersPermutation;
                    for (Function mangle : manglePermutations) {
                        if (!userIsSolved){
                            String manglePermutation = (String) mangle.apply(namePermutation);
                            userManglePermutations.add(manglePermutation);
                            String crypto = jcrypt.crypt(user.salt, manglePermutation);
                            if (crypto.equals(user.password)) {
                                System.out.println("'" + manglePermutation + "' is pass for " + user.firstName + " " + user.surName);
                                unsolvedUsers.remove(finalUserRef);
                                userIsSolved = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!userIsSolved){
                for (Function mangle : charPermutations) {
                    for (int asci = 48; asci < 123; asci++){
                        for (Object usersPermutation : userManglePermutations){
                            String mangleCharPermutations = (String) mangle.apply(new String[]{(String)usersPermutation, String.valueOf((char)asci)});
                            String crypto = jcrypt.crypt(user.salt, mangleCharPermutations);
                            if (crypto.equals(user.password)) {
                                System.out.println("'" + mangleCharPermutations + "' is pass for " + user.firstName + " " + user.surName);
                                unsolvedUsers.remove(finalUserRef);
                                userIsSolved = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Pure name variants are done. Left: " + unsolvedUsers.size());
    }

    private static void tryDictionaryVariants() {
        for (int i = 0; i < dictionary.size(); i++){
            for (Function func : manglePermutations) {
                String wordMangle = (String) func.apply(dictionary.get(i));
                for (int j = 0; j < unsolvedUsers.size(); j++){
                    UserEntry user = unsolvedUsers.get(j);
                    String crypto = jcrypt.crypt(user.salt, wordMangle);
                    if (crypto.equals(user.password)) {
                        System.out.println("'" + wordMangle + "' is pass for " + user.firstName + " " + user.surName);
                        unsolvedUsers.remove(j);
                    }
                }
            }
        }
    }


    private static void addManglePermutationFunctions() {
        Function<String, String> upperCase = (String input) -> input.toUpperCase();
        Function<String, String> lowerCase = (String input) -> input.toLowerCase();
        Function<String, String> capitalize = (String input) -> String.valueOf(input.charAt(0)).toUpperCase() + input.substring(1,input.length()).toLowerCase();
        Function<String, String> nCapitalize = (String input) -> String.valueOf(input.charAt(0)).toLowerCase() + input.substring(1,input.length()).toUpperCase();
        Function<String, String> toggleCaseUpperStart = (String input) -> {
            String[] stringArr = input.split("");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < stringArr.length; i++){
                sb.append(i%2==0 ? stringArr[i].toUpperCase():stringArr[i].toLowerCase());
            }
            return sb.toString();
        };
        Function<String, String> toggleCaseLowerStart = (String input) -> {
            String[] stringArr = input.split("");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < stringArr.length; i++){
                sb.append(i%2==0 ? stringArr[i].toLowerCase():stringArr[i].toUpperCase());
            }
            return sb.toString();
        };
        Function<String, String> removeFirst = (String input) -> input.substring(1,input.length());
        Function<String, String> removeLast = (String input) -> input.substring(0,input.length()-1);
        Function<String, String> reverse = (String input) -> {
            char[] charArr = input.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = charArr.length-1; i>=0; i--){
                sb.append(charArr[i]);
            }
            return sb.toString();
        };
        Function<String, String> duplicate = (String input) -> input+input;

        manglePermutations.add(upperCase);
        manglePermutations.add(lowerCase);
        manglePermutations.add(capitalize);
        manglePermutations.add(nCapitalize);
        manglePermutations.add(toggleCaseUpperStart);
        manglePermutations.add(toggleCaseLowerStart);
        manglePermutations.add(removeFirst);
        manglePermutations.add(removeLast);
        manglePermutations.add(reverse);
        manglePermutations.add(duplicate);
    }

    private static void addNamePermutationFunctions() {
        Function<String[], String> firstOnly = (String[] input) -> input[0].toLowerCase();
        Function<String[], String> lastOnly = (String[] input) -> input[1].toLowerCase();
        Function<String[], String> bothOnly = (String[] input) -> input[0].toLowerCase() + input[1].toUpperCase();
        Function<String[], String> bothCapitalize = (String[] input) -> (String.valueOf(input[0].charAt(0)).toUpperCase() + input[0].substring(1,input[0].length()))
                + (String.valueOf(input[1].charAt(0)).toUpperCase() + input[1].substring(1,input[1].length()));

        Function<String, String> reverse = (String input) -> {
            char[] charArr = input.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = charArr.length-1; i>=0; i--){
                sb.append(charArr[i]);
            }
            return sb.toString();
        };
        Function<String[], String> reflect = (String[] input) -> firstOnly.apply(input) + reverse.apply(lastOnly.apply(input));
        namePermutations.add(firstOnly);
        namePermutations.add(lastOnly);
        namePermutations.add(lastOnly);
        namePermutations.add(bothCapitalize);
        namePermutations.add(bothOnly);
        namePermutations.add(reflect);
    }

    private static void addCharPermutationFunctions() {
        Function<String[], String> appendChar = (String[] input) -> input[0] + input[1];
        Function<String[], String> prependChar = (String[] input) -> input[1] + input[0];
        charPermutations.add(appendChar);
        charPermutations.add(prependChar);
    }

    private static void loadUserInfo(String pswdFileName){
        String basepath = new File("").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(basepath + "/src/" + pswdFileName)));
            String st;
            while ((st = br.readLine()) != null) {
                String[] parts = st.split(":");
                unsolvedUsers.add(new UserEntry(parts[1], parts[4]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found. Make sure the file is in the same directory as PasswordCrack.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadDictionary(String dictionaryName) {
        String basepath = new File("").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(basepath + "/src/" + dictionaryName)));
            String st;
            while ((st = br.readLine()) != null) dictionary.add(st);
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found. Make sure the file is in the same directory as PasswordCrack.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class UserEntry {
        String password;
        String salt;
        String hash;
        String firstName;
        String surName;

        UserEntry(String password, String fullName){
            this.password = password;
            this.salt = password.substring(0,2);
            this.hash = password.substring(2, password.length());
            String[] parts = fullName.split(" ");
            for (String part : parts) {
                if (this.firstName == null) { this.firstName = part; continue; }
                if (this.firstName.contains(".")) { this.firstName = part; continue; }
                if (this.surName == null) { this.surName = part; continue; }
                if (this.surName.contains(".")) { this.surName = part; }
            }
        }
    }
}
