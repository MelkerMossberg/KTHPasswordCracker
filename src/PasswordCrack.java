import java.io.*;
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
        loadDictionary(args[0]);
        loadUserInfo(args[1]);

        addNamePermutationFunctions();
        addManglePermutationFunctions();
        addCharPermutationFunctions();

        tryMostCommon();
        tryNameVariants();
        tryDictionaryVariants();
        System.out.println("Passwords left un-cracked: " + unsolvedUsers.size() + "/20");
    }

    private static void tryMostCommon() {
        String[] mostCommon = ("password,123456,12345678,1234,qwerty,12345,dragon,pussy,baseball,football,letmein,monkey,696969,abc123,mustang,michael,shadow,master,jennifer,111111,2000,jordan,superman,harley,1234567,fuckme,hunter,fuckyou,trustno1,ranger,buster,thomas,tigger,robert,soccer,fuck,batman,test,pass,killer,hockey,george,charlie,andrew,michelle,love,sunshine,jessica,asshole,6969,pepper,daniel,access,123456789,654321,joshua,maggie,starwars,silver,william,dallas,yankees,123123,ashley,666666,hello,amanda,orange,biteme,freedom,computer,sexy,thunder,nicole,ginger,heather,hammer,summer,corvette,taylor,fucker,austin,1111,merlin,matthew,121212,golfer,cheese,princess,martin,chelsea,patrick,richard,diamond,yellow,bigdog,secret,asdfgh,sparky,cowboy,camaro,anthony,matrix,falcon,iloveyou,bailey,guitar,jackson,purple,scooter,phoenix,aaaaaa,morgan,tigers,porsche,mickey,maverick,cookie,nascar,peanut,justin,131313,money,horny,samantha,panties,steelers,joseph,snoopy,boomer,whatever,iceman,smokey,gateway,dakota,cowboys,eagles,chicken,dick,black,zxcvbn,please,andrea,ferrari,knight,hardcore,melissa,compaq,coffee,booboo,bitch,johnny,bulldog,xxxxxx,welcome,james,player,ncc1701,wizard,scooby,charles,junior,internet,bigdick,mike,brandy,tennis,blowjob,banana,monster,spider,lakers,miller,rabbit,enter,mercedes,brandon,steven,fender,john,yamaha,diablo,chris,boston,tiger,marine,chicago,rangers,gandalf,winter,bigtits,barney,edward,raiders,porn,badboy,blowme,spanky,bigdaddy,johnson,chester,london,midnight,blue,fishing,000000,hannah,slayer,11111111,rachel,sexsex,redsox,thx1138,asdf,marlboro,panther,zxcvbnm,arsenal,oliver,qazwsx,mother,victoria,7777777,jasper,angel,david,winner,crystal,golden,butthead,viking,jack,iwantu,shannon,murphy,angels,prince,cameron,girls,madison,wilson,carlos,hooters,willie,startrek,captain,maddog,jasmine,butter,booger,angela,golf,lauren,rocket,tiffany,theman,dennis,liverpoo,flower,forever,green,jackie,muffin,turtle,sophie,danielle,redskins,toyota,jason,sierra,winston,debbie,giants,packers,newyork,jeremy,casper,bubba,112233,sandra,lovers,mountain,united,cooper,driver,tucker,helpme,fucking,pookie,lucky,maxwell,8675309,bear,suckit,gators,5150,222222,shithead,fuckoff,jaguar,monica,fred,happy,hotdog,tits,gemini,lover,xxxxxxxx,777777,canada,nathan,victor,florida,88888888,nicholas,rosebud,metallic,doctor,trouble,success,stupid,tomcat,warrior,peaches,apples,fish,qwertyui,magic,buddy,dolphins,rainbow,gunner,987654,freddy,alexis,braves,cock,2112,1212,cocacola,xavier,dolphin,testing,bond007,member,calvin,voodoo,7777,samson,alex,apollo,fire,tester,walter,beavis,voyager,peter,porno,bonnie,rush2112,beer,apple,scorpio,jonathan,skippy,sydney,scott,red123,power,gordon,travis,beaver,star,jackass,flyers,boobs,232323,zzzzzz,steve,rebecca,scorpion,doggie,legend,ou812,yankee,blazer,bill,runner,birdie,bitches,555555,parker,topgun,asdfasdf,heaven,viper,animal,2222,bigboy,4444,arthur,baby,private,godzilla,donald,williams,lifehack,phantom,dave,rock,august,sammy,cool,brian,platinum,jake,bronco,paul,mark,frank,heka6w2,copper,billy,cumshot,garfield,willow,cunt,little,carter,slut,albert,69696969,kitten,super,jordan23,eagle1,shelby,america,11111,jessie,house,free,123321,chevy,bullshit,white,broncos,horney,surfer,nissan,999999,saturn,airborne,elephant,marvin,shit,action,adidas,qwert,kevin,1313,explorer,walker,police,christin,december,benjamin,wolf,sweet,therock,king,online,dickhead,brooklyn,teresa,cricket,sharon,dexter,racing,penis,gregory,0000,teens,redwings,dreams,michigan,hentai,magnum,87654321,nothing,donkey,trinity,digital,333333,stella,cartman,guinness,123abc,speedy,buffalo,kitty,pimpin,eagle,einstein,kelly,nelson,nirvana,vampire,xxxx,playboy,louise,pumpkin,snowball,test123,girl,sucker,mexico,beatles,fantasy,ford,gibson,celtic,marcus,cherry,cassie,888888,natasha,sniper,chance,genesis,hotrod,reddog,alexande,college,jester,passw0rd,bigcock,smith,lasvegas,carmen,slipknot,3333,death,kimberly,1q2w3e,eclipse,1q2w3e4r,stanley,samuel,drummer,homer,montana,music,aaaa,spencer,jimmy,carolina,colorado,creative,hello1,rocky,goober,friday,bollocks,scotty,abcdef,bubbles,hawaii,fluffy,mine,stephen,horses,thumper,5555,pussies,darkness,asdfghjk,pamela,boobies,buddha,vanessa,sandman,naughty,douglas,honda,matt,azerty,6666,shorty,money1,beach,loveme,4321,simple,poohbear,444444,badass,destiny,sarah,denise,vikings,lizard,melanie,assman,sabrina,nintendo,water,good,howard,time,123qwe,november,xxxxx,october,leather,bastard,young,101010,extreme,hard,password1,vincent,pussy1,lacrosse,hotmail,spooky,amateur,alaska,badger,paradise,maryjane,poop,crazy,mozart,video,russell,vagina,spitfire,anderson,norman,eric,cherokee,cougar,barbara,long,420420,family,horse,enigma,allison,raider,brazil,blonde,jones,55555,dude,drowssap,jeff,school,marshall,lovely,1qaz2wsx,jeffrey,caroline,franklin,booty,molly,snickers,leslie,nipples,courtney,diesel,rocks,eminem,westside,suzuki,daddy,passion,hummer,ladies,zachary,frankie,elvis,reggie,alpha,suckme,simpson,patricia,147147,pirate,tommy,semperfi,jupiter,redrum,freeuser,wanker,stinky,ducati,paris,natalie,babygirl,bishop,windows,spirit,pantera,monday,patches,brutus,houston,smooth,penguin,marley,forest,cream,212121,flash,maximus,nipple,bobby,bradley,vision,pokemon,champion,fireman,indian,softball,picard,system,clinton,cobra,enjoy,lucky1,claire,claudia,boogie,timothy,marines,security,dirty,admin,wildcats,pimp,dancer,hardon,veronica,fucked,abcd1234,abcdefg,ironman,wolverin,remember,great,freepass,bigred,squirt,justice,francis,hobbes,kermit,pearljam,mercury,domino,9999,denver,brooke,rascal,hitman,mistress,simon,tony,bbbbbb,friend,peekaboo,naked,budlight,electric,sluts,stargate,saints,bondage,brittany,bigman,zombie,swimming,duke,qwerty1,babes,scotland,disney,rooster,brenda,mookie,swordfis,candy,duncan,olivia,hunting,blink182,alicia,8888,samsung,bubba1,whore,virginia,general,passport,aaaaaaaa,erotic,liberty,arizona,jesus,abcd,newport,skipper,rolltide,balls,happy1,galore,christ,weasel,242424,wombat,digger,classic,bulldogs,poopoo,accord,popcorn,turkey,jenny,amber,bunny,mouse,007007,titanic,liverpool,dreamer,everton,friends,chevelle,carrie,gabriel,psycho,nemesis,burton,pontiac,connor,eatme,lickme,roland,cumming,mitchell,ireland,lincoln,arnold,spiderma,patriots,goblue,devils,eugene,empire,asdfg,cardinal,brown,shaggy,froggy,qwer,kawasaki,kodiak,people,phpbb,light,54321,kramer,chopper,hooker,honey,whynot,lesbian,lisa,baxter,adam,snake,teen,ncc1701d,qqqqqq,airplane,britney,avalon,sandy,sugar,sublime,stewart,wildcat,raven,scarface,elizabet,123654,trucks,wolfpack,pervert,lawrence,raymond,redhead,american,alyssa,bambam,movie,woody,shaved,snowman,tiger1,chicks,raptor,1969,stingray,shooter,france,stars,madmax,kristen,sports,jerry,789456,garcia,simpsons,lights,ryan,looking,chronic,alison,hahaha,packard,hendrix,perfect,service,spring,srinivas,spike,katie,252525,oscar,brother,bigmac,suck,single,cannon,georgia,popeye,tattoo,texas,party,bullet,taurus,sailor,wolves,panthers,japan,strike,flowers,pussycat,chris1,loverboy,berlin,sticky,marina,tarheels,fisher,russia,connie,wolfgang,testtest,mature,bass,catch22,juice,michael1,nigger,159753,women,alpha1,trooper,hawkeye,head,freaky,dodgers,pakistan,machine,pyramid,vegeta,katana,moose,tinker,coyote,infinity,inside,pepsi,letmein1,bang,control,hercules,morris,james1,tickle,outlaw,browns,billybob,pickle,test1,michele,antonio,sucks,pavilion,changeme,caesar,prelude,tanner,adrian,darkside,bowling,wutang,sunset,robbie,alabama,danger,zeppelin,juan,rusty,pppppp,nick,2001,ping,darkstar,madonna,qwe123,bigone,casino,cheryl,charlie1,mmmmmm,integra,wrangler,apache,tweety,qwerty12,bobafett,simone,none,business,sterling,trevor,transam,dustin,harvey,england,2323,seattle,ssssss,rose,harry,openup,pandora,pussys,trucker,wallace,indigo,storm,malibu,weed,review,babydoll,doggy,dilbert,pegasus,joker,catfish,flipper,valerie,herman,fuckit,detroit,kenneth,cheyenne,bruins,stacey,smoke,joey,seven,marino,fetish,xfiles,wonder,stinger,pizza,babe,pretty,stealth,manutd,gracie,gundam,cessna,longhorn,presario,mnbvcxz,wicked,mustang1,victory,21122112,shelly,awesome,athena,q1w2e3r4,help,holiday,knicks,street,redneck,12341234,casey,gizmo,scully,dragon1,devildog,triumph,eddie,bluebird,shotgun,peewee,ronnie,angel1,daisy,special,metallica,madman,country,impala,lennon,roscoe,omega,access14,enterpri,miranda,search,smitty,blizzard,unicorn,tight,rick,ronald,asdf1234,harrison,trigger,truck,danny,home,winnie,beauty,thailand,1234567890,cadillac,castle,tyler,bobcat,buddy1,sunny,stones,asian,freddie,chuck,butt,loveyou,norton,hellfire,hotsex,indiana,short,panzer,lonewolf,trumpet,colors,blaster,12121212,fireball,logan,precious,aaron,elaine,jungle,atlanta,gold,corona,curtis,nikki,polaris,timber,theone,baller,chipper,orlando,island,skyline,dragons,dogs,benson,licker,goldie,engineer,kong,pencil,basketba,open,hornet,world,linda,barbie,chan,farmer,valentin,wetpussy,indians,larry,redman,foobar,travel,morpheus,bernie,target,141414,hotstuff,photos,laura,savage,holly,rocky1,fuck_inside,dollar,turbo,design,newton,hottie,moon,202020,blondes,4128,lestat,avatar,future,goforit,random,abgrtyu,jjjjjj,cancer,q1w2e3,smiley,goldberg,express,virgin,zipper,wrinkle1,stone,andy,babylon,dong,powers,consumer,dudley,monkey1,serenity,samurai,99999999,bigboobs,skeeter,lindsay,joejoe,master1,aaaaa,chocolat,christia,birthday,stephani,tang,1234qwer,alfred,ball,98765432,maria,sexual,maxima,77777777,sampson,buckeye,highland,kristin,seminole,reaper,bassman,nugget,lucifer,airforce,nasty,watson,warlock,2121,philip,always,dodge,chrissy,burger,bird,snatch,missy,pink,gang,maddie,holmes,huskers,piglet,photo,joanne,hamilton,dodger,paladin,christy,chubby,buckeyes,hamlet,abcdefgh,bigfoot,sunday,manson,goldfish,garden,deftones,icecream,blondie,spartan,julie,harold,charger,brandi,stormy,sherry,pleasure,juventus,rodney,galaxy,holland,escort,zxcvb,planet,jerome,wesley,blues,song,peace,david1,ncc1701e,1966,51505150,cavalier,gambit,karen,sidney,ripper,oicu812,jamie,sister,marie,martha,nylons,aardvark,nadine,minnie,whiskey,bing,plastic,anal,babylon5,chang,savannah,loser,racecar,insane,yankees1,mememe,hansolo,chiefs,fredfred,freak,frog,salmon,concrete,yvonne,zxcv,shamrock,atlantis,warren,wordpass,julian,mariah,rommel,1010,harris,predator,sylvia,massive,cats,sammy1,mister,stud,marathon,rubber,ding,trunks,desire,montreal,justme,faster,kathleen,irish,1999,bertha,jessica1,alpine,sammie,diamonds,tristan,00000,swinger,shan,stallion,pitbull,letmein2,roberto,ready,april,palmer,ming,shadow1,audrey,chong,clitoris,wang,shirley,fuckers,jackoff,bluesky,sundance,renegade,hollywoo,151515,bernard,wolfman,soldier,picture,pierre,ling,goddess,manager,nikita,sweety,titans,hang,fang,ficken,niners,bottom,bubble,hello123,ibanez,webster,sweetpea,stocking,323232,tornado,lindsey,content,bruce,buck,aragorn,griffin,chen,campbell,trojan,christop,newman,wayne,tina,rockstar,father,geronimo,pascal,crimson,brooks,hector,penny,anna,google,camera,chandler,fatcat,lovelove,cody,cunts,waters,stimpy,finger,cindy,wheels,viper1,latin,robin,greenday,987654321,creampie,brendan,hiphop,willy,snapper,funtime,duck,trombone,adult,cotton,cookies,kaiser,mulder,westham,latino,jeep,ravens,aurora,drizzt,madness,energy,kinky,314159,sophia,stefan,slick,rocker,55555555,freeman,french,mongoose,speed,dddddd,hong,henry,hungry,yang,catdog,cheng,ghost,gogogo,randy,tottenha,curious,butterfl,mission,january,singer,sherman,shark,techno,lancer,lalala,autumn,chichi,orion,trixie,clifford,delta,bobbob,bomber,holden,kang,kiss,1968,spunky,liquid,mary,beagle,granny,network,bond,kkkkkk,millie,1973,biggie,beetle,teacher,susan,toronto,anakin,genius,dream,cocks,dang,bush,karate,snakes,bangkok,callie,fuckyou2,pacific,daytona,kelsey,infantry,skywalke,foster,felix,sailing,raistlin,vanhalen,huang,herbert,jacob,blackie,tarzan,strider,sherlock,lang,gong,sang,dietcoke,ultimate,tree,shai,sprite,ting,artist,chai,chao,devil,python,ninja,misty,ytrewq,sweetie,superfly,456789,tian,jing,jesus1,freedom1,dian,drpepper,potter,chou,darren,hobbit,violet,yong,shen,phillip,maurice,gloria,nolimit,mylove,biscuit,yahoo,shasta,sex4me,smoker,smile,pebbles,pics,philly,tong,tintin,lesbians,marlin,cactus,frank1,tttttt,chun,danni,emerald,showme,pirates,lian,dogg,colleen,xiao,xian,tazman,tanker,patton,toshiba,richie,alberto,gotcha,graham,dillon,rang,emily,keng,jazz,bigguy,yuan,woman,tomtom,marion,greg,chaos,fossil,flight,racerx,tuan,creamy,boss,bobo,musicman,warcraft,window,blade,shuang,sheila,shun,lick,jian,microsoft,rong,allen,feng,getsome,sally,quality,kennedy,morrison,1977,beng,wwwwww,yoyoyo,zhang,seng,teddy,joanna,andreas,harder,luke,qazxsw,qian,cong,chuan,deng,nang,boeing,keeper,western,isabelle,1963,subaru,sheng,thuglife,teng,jiong,miao,martina,mang,maniac,pussie,tracey,a1b2c3,clayton,zhou,zhuang,xing,stonecol,snow,spyder,liang,jiang,memphis,regina,ceng,magic1,logitech,chuang,dark,million,blow,sesame,shao,poison,titty,terry,kuan,kuai,kyle,mian,guan,hamster,guai,ferret,florence,geng,duan,pang,maiden,quan,velvet,nong,neng,nookie,buttons,bian,bingo,biao,zhong,zeng,xiong,zhun,ying,zong,xuan,zang,0.0.000,suan,shei,shui,sharks,shang,shua,small,peng,pian,piao,liao,meng,miami,reng,guang,cang,change,ruan,diao,luan,lucas,qing,chui,chuo,cuan,nuan,ning,heng,huan,kansas,muscle,monroe,weng,whitney,1passwor,bluemoon,zhui,zhua,xiang,zheng,zhen,zhei,zhao,zhan,yomama,zhai,zhuo,zuan,tarheel,shou,shuo,tiao,lady,leonard,leng,kuang,jiao,13579,basket,qiao,qiong,qiang,chuai,nian,niao,niang,huai,22222222,bianca,zhuan,zhuai,shuan,shuai,stardust,jumper,margaret,archie,66666666,charlott,forget,qwertz,bones,history,milton,waterloo,2002,stuff,11223344,office,oldman,preston,trains,murray,vertigo,246810,black1,swallow,smiles,standard,alexandr,parrot,luther,user,nicolas,1976,surfing,pioneer,pete,masters,apple1,asdasd,auburn,hannibal,frontier,panama,lucy,buffy,brianna,welcome1,vette,blue22,shemale,111222,baggins,groovy,global,turner,181818,1979,blades,spanking,life,byteme,lobster,collins,dawg,hilton,japanese,1970,1964,2424,polo,markus,coco,deedee,mikey,1972,171717,1701,strip,jersey,green1,capital,sasha,sadie,putter,vader,seven7,lester,marcel,banshee,grendel,gilbert,dicks,dead,hidden,iloveu,1980,sound,ledzep,michel,147258,female,bugger,buffett,bryan,hell,kristina,molson,2020,wookie,sprint,thanks,jericho,102030,grace,fuckin,mandy,ranger1,trebor,deepthroat,bonehead,molly1,mirage,models,1984,2468,stuart,showtime,squirrel,pentium,mario,anime,gator,powder,twister,connect,neptune,bruno,butts,engine,eatshit,mustangs,woody1,shogun,septembe,pooh,jimbo,roger,annie,bacon,center,russian,sabine,damien,mollie,voyeur,2525,363636,leonardo,camel,chair,germany,giant,qqqq,nudist,bone,sleepy,tequila,megan,fighter,garrett,dominic,obiwan,makaveli,vacation,walnut,1974,ladybug,cantona,ccbill,satan,rusty1,passwor1,columbia,napoleon,dusty,kissme,motorola,william1,1967,zzzz,skater,smut,play,matthew1,robinson,valley,coolio,dagger,boner,bull,horndog,jason1,blake,penguins,rescue,griffey,8j4ye3uz,californ,champs,qwertyuiop,portland,queen,colt45,boat,xxxxxxx,xanadu,tacoma,mason,carpet,gggggg,safety,palace,italia,stevie,picturs,picasso,thongs,tempest,ricardo,roberts,asd123,hairy,foxtrot,gary,nimrod,hotboy,343434,1111111,asdfghjkl,goose,overlord,blood,wood,stranger,454545,shaolin,sooners,socrates,spiderman,peanuts,maxine,rogers,13131313,andrew1,filthy,donnie,ohyeah,africa,national,kenny,keith,monique,intrepid,jasmin,pickles,assass,fright,potato,darwin,hhhhhh,kingdom,weezer,424242,pepsi1,throat,romeo,gerard,looker,puppy,butch,monika,suzanne,sweets,temple,laurie,josh,megadeth,analsex,nymets,ddddddd,bigballs,support,stick,today,down,oakland,oooooo,qweasd,chucky,bridge,carrot,chargers,discover,dookie,condor,night,butler,hoover,horny1,isabella,sunrise,sinner,jojo,megapass,martini,assfuck,grateful,ffffff,abigail,esther,mushroom,janice,jamaica,wright,sims,space,there,timmy,7654321,77777,cccccc,gizmodo,roxanne,ralph,tractor,cristina,dance,mypass,hongkong,helena,1975,blue123,pissing,thomas1,redred,rich,basketball,attack,cash,satan666,drunk,dixie,dublin,bollox,kingkong,katrina,miles,1971,22222,272727,sexx,penelope,thompson,anything,bbbb,battle,grizzly,passat,porter,tracy,defiant,bowler,knickers,monitor,wisdom,wild,slappy,thor,letsgo,robert1,feet,rush,brownie,hudson,098765,playing,playtime,lightnin,melvin,atomic,bart,hawk,goku,glory,llllll,qwaszx,cosmos,bosco,knights,bentley,beast,slapshot,lewis,assword,frosty,gillian,sara,dumbass,mallard,dddd,deanna,elwood,wally,159357,titleist,angelo,aussie,guest,golfing,doobie,loveit,chloe,elliott,werewolf,vipers,janine,1965,blabla,surf,sucking,tardis,serena,shelley,thegame,legion,rebels,fernando,fast,gerald,sarah1,double,onelove,loulou,toto,crash,blackcat,0007,tacobell,soccer1,jedi,manuel,method,river,chase,ludwig,poopie,derrick,boob,breast,kittycat,isabel,belly,pikachu,thunder1,thankyou,jose,celeste,celtics,frances,frogger,scoobydo,sabbath,coltrane,budman,willis,jackal,bigger,zzzzz,silvia,sooner,licking,gopher,geheim,lonestar,primus,pooper,newpass,brasil,heather1,husker,element,moomoo,beefcake,zzzzzzzz,tammy,shitty,smokin,personal,jjjj,anthony1,anubis,backup,gorilla,fuckface,painter,lowrider,punkrock,traffic,claude,daniela,dale,delta1,nancy,boys,easy,kissing,kelley,wendy,theresa,amazon,alan,fatass,dodgeram,dingdong,malcolm,qqqqqqqq,breasts,boots,honda1,spidey,poker,temp,johnjohn,miguel,147852,archer,asshole1,dogdog,tricky,crusader,weather,syracuse,spankme,speaker,meridian,amadeus,back,harley1,falcons,dorothy,turkey50,kenwood,keyboard,ilovesex,1978,blackman,shazam,shalom,lickit,jimbob,richmond,roller,carson,check,fatman,funny,garbage,sandiego,loving,magnus,cooldude,clover,mobile,bell,payton,plumber,texas1,tool,topper,jenna,mariners,rebel,harmony,caliente,celica,fletcher,german,diana,oxford,osiris,orgasm,punkin,porsche9,tuesday,close,breeze,bossman,kangaroo,billie,latinas,judith,astros,scruffy,donna,qwertyu,davis,hearts,kathy,jammer,java,springer,rhonda,ricky,1122,goodtime,chelsea1,freckles,flyboy,doodle,city,nebraska,bootie,kicker,webmaster,vulcan,iverson,191919,blueeyes,stoner,321321,farside,rugby,director,pussy69,power1,bobbie,hershey,hermes,monopoly,west,birdman,blessed,blackjac,southern,peterpan,thumbs,lawyer,melinda,fingers,fuckyou1,rrrrrr,a1b2c3d4,coke,nicola,bohica,heart,elvis1,kids,blacky,stories,sentinel,snake1,phoebe,jesse,richard1,1234abcd,guardian,candyman,fisting,scarlet,dildo,pancho,mandingo,lucky7,condom,munchkin,billyboy,summer1,student,sword,skiing,sergio,site,sony,thong,rootbeer,assassin,cassidy,frederic,fffff,fitness,giovanni,scarlett,durango,postal,achilles,dawn,dylan,kisses,warriors,imagine,plymouth,topdog,asterix,hallo,cameltoe,fuckfuck,bridget,eeeeee,mouth,weird,will,sithlord,sommer,toby,theking,juliet,avenger,backdoor,goodbye,chevrole,faith,lorraine,trance,cosworth,brad,houses,homers,eternity,kingpin,verbatim,incubus,1961,blond,zaphod,shiloh,spurs,station,jennie,maynard,mighty,aliens,hank,charly,running,dogman,omega1,printer,aggies,chocolate,deadhead,hope,javier,bitch1,stone55,pineappl,thekid,lizzie,rockets,ashton,camels,formula,forrest,rosemary,oracle,rain,pussey,porkchop,abcde,clancy,nellie,mystic,inferno,blackdog,steve1,pauline,alexander,alice,alfa,grumpy,flames,scream,lonely,puffy,proxy,valhalla,unreal,cynthia,herbie,engage,yyyyyy,010101,solomon,pistol,melody,celeb,flying,gggg,santiago,scottie,oakley,portugal,a12345,newbie,mmmm,venus,1qazxsw2,beverly,zorro,work,writer,stripper,sebastia,spread,phil,tobias,links,members,metal,1221,andre,565656,funfun,trojans,again,cyber,hurrican,moneys,1x2zkg8w,zeus,thing,tomato,lion,atlantic,celine,usa123,trans,account,aaaaaaa,homerun,hyperion,kevin1,blacks,44444444,skittles,sean,hastings,fart,gangbang,fubar,sailboat,older,oilers,craig,conrad,church,damian,dean,broken,buster1,hithere,immortal,sticks,pilot,peters,lexmark,jerkoff,maryland,anders,cheers,possum,columbus,cutter,muppet,beautiful,stolen,swordfish,sport,sonic,peter1,jethro,rockon,asdfghj,pass123,paper,pornos,ncc1701a,bootys,buttman,bonjour,escape,1960,becky,bears,362436,spartans,tinman,threesom,lemons,maxmax,1414,bbbbb,camelot,chad,chewie,gogo,fusion,saint,dilligaf,nopass,myself,hustler,hunter1,whitey,beast1,yesyes,spank,smudge,pinkfloy,patriot,lespaul,annette,hammers,catalina,finish,formula1,sausage,scooter1,orioles,oscar1,over,colombia,cramps,natural,eating,exotic,iguana,bella,suckers,strong,sheena,start,slave,pearl,topcat,lancelot,angelica,magelan,racer,ramona,crunch,british,button,eileen,steph,456123,skinny,seeking,rockhard,chief,filter,first,freaks,sakura,pacman,poontang,dalton,newlife,homer1,klingon,watcher,walleye,tasha,tasty,sinatra,starship,steel,starbuck,poncho,amber1,gonzo,grover,catherin,carol,candle,firefly,goblin,scotch,diver,usmc,huskies,eleven,kentucky,kitkat,israel,beckham,bicycle,yourmom,studio,tara,33333333,shane,splash,jimmy1,reality,12344321,caitlin,focus,sapphire,mailman,raiders1,clark,ddddd,hopper,excalibu,more,wilbur,illini,imperial,phillips,lansing,maxx,gothic,golfball,carlton,camille,facial,front242,macdaddy,qwer1234,vectra,cowboys1,crazy1,dannyboy,jane,betty,benny,bennett,leader,martinez,aquarius,barkley,hayden,caught,franky,ffff,floyd,sassy,pppp,pppppppp,prodigy,clarence,noodle,eatpussy,vortex,wanking,beatrice,billy1,siemens,pedro,phillies,research,groups,carolyn,chevy1,cccc,fritz,gggggggg,doughboy,dracula,nurses,loco,madrid,lollipop,trout,utopia,chrono,cooler,conner,nevada,wibble,werner,summit,marco,marilyn,1225,babies,capone,fugazi,panda,mama,qazwsxed,puppies,triton,9876,command,nnnnnn,ernest,momoney,iforgot,wolfie,studly,shawn,renee,alien,hamburg,81fukkc,741852,catman,china,forgot,gagging,scott1,drew,oregon,qweqwe,train,crazybab,daniel1,cutlass,brothers,holes,heidi,mothers,music1,what,walrus,1957,bigtime,bike,xtreme,simba,ssss,rookie,angie,bathing,fresh,sanchez,rotten,maestro,luis,look,turbo1,99999,butthole,hhhh,elijah,monty,bender,yoda,shania,shock,phish,thecat,rightnow,reagan,baddog,asia,greatone,gateway1,randall,abstr,napster,brian1,bogart,high,hitler,emma,kill,weaver,wildfire,jackson1,isaiah,1981,belinda,beaner,yoyo,0.0.0.000,super1,select,snuggles,slutty,some,phoenix1,technics,toon,raven1,rayray,123789,1066,albion,greens,fashion,gesperrt,santana,paint,powell,credit,darling,mystery,bowser,bottle,brucelee,hehehe,kelly1,mojo,1998,bikini,woofwoof,yyyy,strap,sites,spears,theodore,julius,richards,amelia,central,f**k,nyjets,punisher,username,vanilla,twisted,bryant,brent,bunghole,here,elizabeth,erica,kimber,viagra,veritas,pony,pool,titts,labtec,lifetime,jenny1,masterbate,mayhem,redbull,govols,gremlin,505050,gmoney,rupert,rovers,diamond1,lorenzo,trident,abnormal,davidson,deskjet,cuddles,nice,bristol,karina,milano,vh5150,jarhead,1982,bigbird,bizkit,sixers,slider,star69,starfish,penetration,tommy1,john316,meghan,michaela,market,grant,caligula,carl,flicks,films,madden,railroad,cosmo,cthulhu,bradford,br0d3r,military,bearbear,swedish,spawn,patrick1,polly,these,todd,reds,anarchy,groove,franco,fuckher,oooo,tyrone,vegas,airbus,cobra1,christine,clips,delete,duster,kitty1,mouse1,monkeys,jazzman,1919,262626,swinging,stroke,stocks,sting,pippen,labrador,jordan1,justdoit,meatball,females,saturday,park,vector,cooter,defender,desert,demon,nike,bubbas,bonkers,english,kahuna,wildman,4121,sirius,static,piercing,terror,teenage,leelee,marissa,microsof,mechanic,robotech,rated,hailey,chaser,sanders,salsero,nuts,macross,quantum,rachael,tsunami,universe,daddy1,cruise,nguyen,newpass6,nudes,hellyeah,vernon,1959,zaq12wsx,striker,sixty,steele,spice,spectrum,smegma,thumb,jjjjjjjj,mellow,astrid,cancun,cartoon,sabres,samiam,pants,oranges,oklahoma,lust,coleman,denali,nude,noodles,buzz,brest,hooter,mmmmmmmm,warthog,bloody,blueblue,zappa,wolverine,sniffing,lance,jean,jjjjj,harper,calico,freee,rover,door,pooter,closeup,bonsai,evelyn,emily1,kathryn,keystone,iiii,1955,yzerman,theboss,tolkien,jill,megaman,rasta,bbbbbbbb,bean,handsome,hal9000,goofy,gringo,gofish,gizmo1,samsam,scuba,onlyme,tttttttt,corrado,clown,clapton,deborah,boris,bulls,vivian,jayhawk,bethany,wwww,sharky,seeker,ssssssss,somethin,pillow,thesims,lighter,lkjhgf,melissa1,marcius2,barry,guiness,gymnast,casey1,goalie,godsmack,doug,lolo,rangers1,poppy,abby,clemson,clipper,deeznuts,nobody,holly1,elliot,eeee,kingston,miriam,belle,yosemite,sucked,sex123,sexy69,pic\\'s,tommyboy,lamont,meat,masterbating,marianne,marc,gretzky,happyday,frisco,scratch,orchid,orange1,manchest,quincy,unbelievable,aberdeen,dawson,nathalie,ne1469,boxing,hill,korn,intercourse,161616,1985,ziggy,supersta,stoney,senior,amature,barber,babyboy,bcfields,goliath,hack,hardrock,children,frodo,scout,scrappy,rosie,qazqaz,tracker,active,craving,commando,cohiba,deep,cyclone,dana,bubba69,katie1,mpegs,vsegda,jade,irish1,better,sexy1,sinclair,smelly,squerting,lions,jokers,jeanette,julia,jojojo,meathead,ashley1,groucho,cheetah,champ,firefox,gandalf1,packer,magnolia,love69,tyler1,typhoon,tundra,bobby1,kenworth,village,volley,beth,wolf359,0420,000007,swimmer,skydive,smokes,patty,peugeot,pompey,legolas,kristy,redhot,rodman,redalert,having,grapes,4runner,carrera,floppy,dollars,ou8122,quattro,adams,cloud9,davids,nofear,busty,homemade,mmmmm,whisper,vermont,webmaste,wives,insertion,jayjay,philips,phone,topher,tongue,temptress,midget,ripken,havefun,gretchen,canon,celebrity,five,getting,ghetto,direct,otto,ragnarok,trinidad,usnavy,conover,cruiser,dalshe,nicole1,buzzard,hottest,kingfish,misfit,moore,milfnew,warlord,wassup,bigsexy,blackhaw,zippy,shearer,tights,thursday,kungfu,labia,journey,meatloaf,marlene,rider,area51,batman1,bananas,636363,cancel,ggggg,paradox,mack,lynn,queens,adults,aikido,cigars,nova,hoosier,eeyore,moose1,warez,interacial,streaming,313131,pertinant,pool6123,mayday,rivers,revenge,animated,banker,baddest,gordon24,ccccc,fortune,fantasies,touching,aisan,deadman,homepage,ejaculation,whocares,iscool,jamesbon,1956,1pussy,womam,sweden,skidoo,spock,sssss,petra,pepper1,pinhead,micron,allsop,amsterda,army,aside,gunnar,666999,chip,foot,fowler,february,face,fletch,george1,sapper,science,sasha1,luckydog,lover1,magick,popopo,public,ultima,derek,cypress,booker,businessbabe,brandon1,edwards,experience,vulva,vvvv,jabroni,bigbear,yummy,010203,searay,secret1,showing,sinbad,sexxxx,soleil,software,piccolo,thirteen,leopard,legacy,jensen,justine,memorex,marisa,mathew,redwing,rasputin,134679,anfield,greenbay,gore,catcat,feather,scanner,pa55word,contortionist,danzig,daisy1,hores,erik,exodus,vinnie,iiiiii,zero,1001,subway,tank,second,snapple,sneakers,sonyfuck,picks,poodle,test1234,their,llll,junebug,june,marker,mellon,ronaldo,roadkill,amanda1,asdfjkl,beaches,greene,great1,cheerleaers,force,doitnow,ozzy,madeline,radio,tyson,christian,daphne,boxster,brighton,housewifes,emmanuel,emerson,kkkk,mnbvcx,moocow,vides,wagner,janet,1717,bigmoney,blonds,1000,storys,stereo,4545,420247,seductive,sexygirl,lesbean,live,justin1,124578,animals,balance,hansen,cabbage,canadian,gangbanged,dodge1,dimas,lori,loud,malaka,puss,probes,adriana,coolman,crawford,dante,nacked,hotpussy,erotica,kool,mirror,wearing,implants,intruder,bigass,zenith,woohoo,womans,tanya,tango,stacy,pisces,laguna,krystal,maxell,andyod22,barcelon,chainsaw,chickens,flash1,downtown,orgasms,magicman,profit,pusyy,pothead,coconut,chuckie,contact,clevelan,designer,builder,budweise,hotshot,horizon,hole,experienced,mondeo,wifes,1962,strange,stumpy,smiths,sparks,slacker,piper,pitchers,passwords,laptop,jeremiah,allmine,alliance,bbbbbbb,asscock,halflife,grandma,hayley,88888,cecilia,chacha,saratoga,sandy1,santos,doogie,number,positive,qwert40,transexual,crow,close-up,darrell,bonita,ib6ub9,volvo,jacob1,iiiii,beastie,sunnyday,stoned,sonics,starfire,snapon,pictuers,pepe,testing1,tiberius,lisalisa,lesbain,litle,retard,ripple,austin1,badgirl,golfgolf,flounder,garage,royals,dragoon,dickie,passwor,ocean,majestic,poppop,trailers,dammit,nokia,bobobo,br549,emmitt,knock,minime,mikemike,whitesox,1954,3232,353535,seamus,solo,sparkle,sluttey,pictere,titten,lback,1024,angelina,goodluck,charlton,fingerig,gallaries,goat,ruby,passme,oasis,lockerroom,logan1,rainman,twins,treasure,absolutely,club,custom,cyclops,nipper,bucket,homepage-,hhhhh,momsuck,indain,2345,beerbeer,bimmer,susanne,stunner,stevens,456456,shell,sheba,tootsie,tiny,testerer,reefer,really,1012,harcore,gollum,545454,chico,caveman,carole,fordf150,fishes,gaymen,saleen,doodoo,pa55w0rd,looney,presto,qqqqq,cigar,bogey,brewer,helloo,dutch,kamikaze,monte,wasser,vietnam,visa,japanees,0123,swords,slapper,peach,jump,marvel,masterbaiting,march,redwood,rolling,1005,ametuer,chiks,cathy,callaway,fucing,sadie1,panasoni,mamas,race,rambo,unknown,absolut,deacon,dallas1,housewife,kristi,keywest,kirsten,kipper,morning,wings,idiot,18436572,1515,beating,zxczxc,sullivan,303030,shaman,sparrow,terrapin,jeffery,masturbation,mick,redfish,1492,angus,barrett,goirish,hardcock,felicia,forfun,galary,freeporn,duchess,olivier,lotus,pornographic,ramses,purdue,traveler,crave,brando,enter1,killme,moneyman,welder,windsor,wifey,indon,yyyyy,stretch,taylor1,4417,shopping,picher,pickup,thumbnils,johnboy,jets,jess,maureen,anne,ameteur,amateurs,apollo13,hambone,goldwing,5050,charley,sally1,doghouse,padres,pounding,quest,truelove,underdog,trader,crack,climber,bolitas,bravo,hohoho,model,italian,beanie,beretta,wrestlin,stroker,tabitha,sherwood,sexyman,jewels,johannes,mets,marcos,rhino,bdsm,balloons,goodman,grils,happy123,flamingo,games,route66,devo,dino,outkast,paintbal,magpie,llllllll,twilight,critter,christie,cupcake,nickel,bullseye,krista,knickerless,mimi,murder,videoes,binladen,xerxes,slim,slinky,pinky,peterson,thanatos,meister,menace,ripley,retired,albatros,balloon,bank,goten,5551212,getsdown,donuts,divorce,nwo4life,lord,lost,underwear,tttt,comet,deer,damnit,dddddddd,deeznutz,nasty1,nonono,nina,enterprise,eeeee,misfit99,milkman,vvvvvv,isaac,1818,blueboy,beans,bigbutt,wyatt,tech,solution,poetry,toolman,laurel,juggalo,jetski,meredith,barefoot,50spanks,gobears,scandinavian,original,truman,cubbies,nitram,briana,ebony,kings,warner,bilbo,yumyum,zzzzzzz,stylus,321654,shannon1,server,secure,silly,squash,starman,steeler,staples,phrases,techniques,laser,135790,allan,barker,athens,cbr600,chemical,fester,gangsta,fucku2,freeze,game,salvador,droopy,objects,passwd,lllll,loaded,louis,manchester,losers,vedder,clit,chunky,darkman,damage,buckshot,buddah,boobed,henti,hillary,webber,winter1,ingrid,bigmike,beta,zidane,talon,slave1,pissoff,person,thegreat,living,lexus,matador,readers,riley,roberta,armani,ashlee,goldstar,5656,cards,fmale,ferris,fuking,gaston,fucku,ggggggg,sauron,diggler,pacers,looser,pounded,premier,pulled,town,trisha,triangle,cornell,collin,cosmic,deeper,depeche,norway,bright,helmet,kristine,kendall,mustard,misty1,watch,jagger,bertie,berger,word,3x7pxr,silver1,smoking,snowboar,sonny,paula,penetrating,photoes,lesbens,lambert,lindros,lillian,roadking,rockford,1357,143143,asasas,goodboy,898989,chicago1,card,ferrari1,galeries,godfathe,gawker,gargoyle,gangster,rubble,rrrr,onetime,pussyman,pooppoop,trapper,twenty,abraham,cinder,company,newcastl,boricua,bunny1,boxer,hotred,hockey1,hooper,edward1,evan,kris,misery,moscow,milk,mortgage,bigtit,show,snoopdog,three,lionel,leanne,joshua1,july,1230,assholes,cedric,fallen,farley,gene,frisky,sanity,script,divine,dharma,lucky13,property,tricia,akira,desiree,broadway,butterfly,hunt,hotbox,hootie,heat,howdy,earthlink,karma,kiteboy,motley,westwood,1988,bert,blackbir,biggles,wrench,working,wrestle,slippery,pheonix,penny1,pianoman,tomorrow,thedude,jenn,jonjon,jones1,mattie,memory,micheal,roadrunn,arrow,attitude,azzer,seahawks,diehard,dotcom,lola,tunafish,chivas,cinnamon,clouds,deluxe,northern,nuclear,north,boom,boobie,hurley,krishna,momomo,modles,volume,23232323,bluedog,wwwwwww,zerocool,yousuck,pluto,limewire,link,joung,marcia,awnyce,gonavy,haha,films+pic+galeries,fabian,francois,girsl,fuckthis,girfriend,rufus,drive,uncencored,a123456,airport,clay,chrisbln,combat,cygnus,cupoi,never,netscape,brett,hhhhhhhh,eagles1,elite,knockers,kendra,mommy,1958,tazmania,shonuf,piano,pharmacy,thedog,lips,jillian,jenkins,midway,arsenal1,anaconda,australi,gromit,gotohell,787878,66666,carmex2,camber,gator1,ginger1,fuzzy,seadoo,dorian,lovesex,rancid,uuuuuu,911911,nature,bulldog1,helen,health,heater,higgins,kirk,monalisa,mmmmmmm,whiteout,virtual,ventura,jamie1,japanes,james007,2727,2469,blam,bitchass,believe,zephyr,stiffy,sweet1,silent,southpar,spectre,tigger1,tekken,lenny,lakota,lionking,jjjjjjj,medical,megatron,1369,hawaiian,gymnastic,golfer1,gunners,7779311,515151,famous,glass,screen,rudy,royal,sanfran,drake,optimus,panther1,love1,mail,maggie1,pudding,venice,aaron1,delphi,niceass,bounce,busted,house1,killer1,miracle,momo,musashi,jammin,2003,234567,wp2003wp,submit,silence,sssssss,state,spikes,sleeper,passwort,toledo,kume,media,meme,medusa,mantis,remote,reading,reebok,1017,artemis,hampton,harry1,cafc91,fettish,friendly,oceans,oooooooo,mango,ppppp,trainer,troy,uuuu,909090,cross,death1,news,bullfrog,hokies,holyshit,eeeeeee,mitch,jasmine1,&amp,&amp;,sergeant,spinner,leon,jockey,records,right,babyblue,hans,gooner,474747,cheeks,cars,candice,fight,glow,pass1234,parola,okokok,pablo,magical,major,ramsey,poseidon,989898,confused,circle,crusher,cubswin,nnnn,hollywood,erin,kotaku,milo,mittens,whatsup,vvvvv,iomega,insertions,bengals,bermuda,biit,yellow1,012345,spike1,south,sowhat,pitures,peacock,pecker,theend,juliette,jimmie,romance,augusta,hayabusa,hawkeyes,castro,florian,geoffrey,dolly,lulu,qaz123,usarmy,twinkle,cloud,chuckles,cold,hounddog,hover,hothot,europa,ernie,kenshin,kojak,mikey1,water1,196969,because,wraith,zebra,wwwww,33333,simon1,spider1,snuffy,philippe,thunderb,teddy1,lesley,marino13,maria1,redline,renault,aloha,antoine,handyman,cerberus,gamecock,gobucks,freesex,duffman,ooooo,papa,nuggets,magician,longbow,preacher,porno1,county,chrysler,contains,dalejr,darius,darlene,dell,navy,buffy1,hedgehog,hoosiers,honey1,hott,heyhey,europe,dutchess,everest,wareagle,ihateyou,sunflowe,3434,senators,shag,spoon,sonoma,stalker,poochie,terminal,terefon,laurence,maradona,maryann,marty,roman,1007,142536,alibaba,america1,bartman,astro,goth,century,chicken1,cheater,four,ghost1,passpass,oral,r2d2c3po,civic,cicero,myxworld,kkkkk,missouri,wishbone,infiniti,jameson,1a2b3c,1qwerty,wonderboy,skip,shojou,stanford,sparky1,smeghead,poiuy,titanium,torres,lantern,jelly,jeanne,meier,1213,bayern,basset,gsxr750,cattle,charlene,fishing1,fullmoon,gilles,dima,obelix,popo,prissy,ramrod,unique,absolute,bummer,hotone,dynasty,entry,konyor,missy1,moses,282828,yeah,xyz123,stop,426hemi,404040,seinfeld,simmons,pingpong,lazarus,matthews,marine1,manning,recovery,12345a,beamer,babyface,greece,gustav,7007,charity,camilla,ccccccc,faggot,foxy,frozen,gladiato,duckie,dogfood,paranoid,packers1,longjohn,radical,tuna,clarinet,claudio,circus,danny1,novell,nights,bonbon,kashmir,kiki,mortimer,modelsne,moondog,monaco,vladimir,insert,1953,zxc123,supreme,3131,sexxx,selena,softail,poipoi,pong,together,mars,martin1,rogue,alone,avalanch,audia4,55bgates,cccccccc,chick,came11,figaro,geneva,dogboy,dnsadm,dipshit,paradigm,othello,operator,officer,malone,post,rafael,valencia,tripod,choice,chopin,coucou,coach,cocksuck,common,creature,borussia,book,browning,heritage,hiziad,homerj,eight,earth,millions,mullet,whisky,jacques,store,4242,speedo,starcraf,skylar,spaceman,piggy,pierce,tiger2,legos,lala,jezebel,judy,joker1,mazda,barton,baker,727272,chester1,fishman,food,rrrrrrrr,sandwich,dundee,lumber,magazine,radar,ppppppp,tranny,aaliyah,admiral,comics,cleo,delight,buttfuck,homeboy,eternal,kilroy,kellie,khan,violin,wingman,walmart,bigblue,blaze,beemer,beowulf,bigfish,yyyyyyy,woodie,yeahbaby,0123456,tbone,style,syzygy,starter,lemon,linda1,merlot,mexican,11235813,anita,banner,bangbang,badman,barfly,grease,carla,charles1,ffffffff,screw,doberman,diane,dogshit,overkill,counter,coolguy,claymore,demons,demo,nomore,normal,brewster,hhhhhhh,hondas,iamgod,enterme,everett,electron,eastside,kayla,minimoni,mybaby,wildbill,wildcard,ipswich,200000,bearcat,zigzag,yyyyyyyy,xander,sweetnes,369369,skyler,skywalker,pigeon,peyton,tipper,lilly,asdf123,alphabet,asdzxc,babybaby,banane,barnes,guyver,graphics,grand,chinook,florida1,flexible,fuckinside,otis,ursitesux,tototo,trust,tower,adam12,christma,corey,chrome,buddie,bombers,bunker,hippie,keegan,misfits,vickie,292929,woofer,wwwwwwww,stubby,sheep,secrets,sparta,stang,spud,sporty,pinball,jorge,just4fun,johanna,maxxxx,rebecca1,gunther,fatima,fffffff,freeway,garion,score,rrrrr,sancho,outback,maggot,puddin,trial,adrienne,987456,colton,clyde,brain,brains,hoops,eleanor,dwayne,kirby,mydick,villa,19691969,bigcat,becker,shiner,silverad,spanish,templar,lamer,juicy,marsha,mike1,maximum,rhiannon,real,1223,10101010,arrows,andres,alucard,baldwin,baron,avenue,ashleigh,haggis,channel,cheech,safari,ross,dog123,orion1,paloma,qwerasdf,presiden,vegitto,trees,969696,adonis,colonel,cookie1,newyork1,brigitte,buddyboy,hellos,heineken,dwight,eraser,kerstin,motion,moritz,millwall,visual,jaybird,1983,beautifu,bitter,yvette,zodiac,steven1,sinister,slammer,smashing,slick1,sponge,teddybea,theater,this,ticklish,lipstick,jonny,massage,mann,reynolds,ring,1211,amazing,aptiva,applepie,bailey1,guitar1,chanel,canyon,gagged,fuckme1,rough,digital1,dinosaur,punk,98765,90210,clowns,cubs,daniels,deejay,nigga,naruto,boxcar,icehouse,hotties,electra,kent,widget,india,insanity,1986,2004,best,bluefish,bingo1,*****,stratus,strength,sultan,storm1,44444,4200,sentnece,season,sexyboy,sigma,smokie,spam,point,pippo,ticket,temppass,joel,manman,medicine,1022,anton,almond,bacchus,aztnm,axio,awful,bamboo,hakr,gregor,hahahaha,5678,casanova,caprice,camero1,fellow,fountain,dupont,dolphin1,dianne,paddle,magnet,qwert1,pyon,porsche1,tripper,vampires,coming,noway,burrito,bozo,highheel,hughes,hookem,eddie1,ellie,entropy,kkkkkkkk,kkkkkkk,illinois,jacobs,1945,1951,24680,21212121,100000,stonecold,taco,subzero,sharp,sexxxy,skolko,shanna,skyhawk,spurs1,sputnik,piazza,testpass,letter,lane,kurt,jiggaman,matilda,1224,harvard,hannah1,525252,4ever,carbon,chef,federico,ghosts,gina,scorpio1,rt6ytere,madison1,loki,raquel,promise,coolness,christina,coldbeer,citadel,brittney,highway,evil,monarch,morgan1,washingt,1997,bella1,berry,yaya,yolanda,superb,taxman,studman,stephanie,3636,sherri,sheriff,shepherd,poland,pizzas,tiffany1,toilet,latina,lassie,larry1,joseph1,mephisto,meagan,marian,reptile,rico,razor,1013,barron,hammer1,gypsy,grande,carroll,camper,chippy,cat123,call,chimera,fiesta,glock,glenn,domain,dieter,dragonba,onetwo,nygiants,odessa,password2,louie,quartz,prowler,prophet,towers,ultra,cocker,corleone,dakota1,cumm,nnnnnnn,natalia,boxers,hugo,heynow,hollow,iceberg,elvira,kittykat,kate,kitchen,wasabi,vikings1,impact,beerman,string,sleep,splinter,snoopy1,pipeline,pocket,legs,maple,mickey1,manuela,mermaid,micro,meowmeow,redbird,alisha,baura,battery,grass,chevys,chestnut,caravan,carina,charmed,fraser,frogman,diving,dogger,draven,drifter,oatmeal,paris1,longdong,quant4307s,rachel1,vegitta,cole,cobras,corsair,dadada,noelle,mylife,nine,bowwow,body,hotrats,eastwood,moonligh,modena,wave,illusion,iiiiiii,jayhawks,birgit,zone,sutton,susana,swingers,shocker,shrimp,sexgod,squall,stefanie,squeeze,soul,patrice,poiu,players,tigers1,toejam,tickler,line,julie1,jimbo1,jefferso,juanita,michael2,rodeo,robot,1023,annie1,bball,guess,happy2,charter,farm,flasher,falcon1,fiction,fastball,gadget,scrabble,diaper,dirtbike,dinner,oliver1,partner,paco,lucille,macman,poopy,popper,postman,ttttttt,ursula,acura,cowboy1,conan,daewoo,cyrus,customer,nation,nemrac58,nnnnn,nextel,bolton,bobdylan,hopeless,eureka,extra,kimmie,kcj9wx5n,killbill,musica,volkswag,wage,windmill,wert,vintage,iloveyou1,itsme,bessie,zippo,311311,starligh,smokey1,spot,snappy,soulmate,plasma,thelma,tonight,krusty,just4me,mcdonald,marius,rochelle,rebel1,1123,alfredo,aubrey,audi,chantal,fick,goaway,roses,sales,rusty2,dirt,dogbone,doofus,ooooooo,oblivion,mankind,luck,mahler,lllllll,pumper,puck,pulsar,valkyrie,tupac,compass,concorde,costello,cougars,delaware,niceguy,nocturne,bob123,boating,bronze,hopkins,herewego,hewlett,houhou,hubert,earnhard,eeeeeeee,keller,mingus,mobydick,venture,verizon,imation,1950,1948,1949,223344,bigbig,blossom,zack,wowwow,sissy,skinner,spiker,square,snooker,sluggo,player1,junk,jeannie,jsbach,jumbo,jewel,medic,robins,reddevil,reckless,123456a,1125,1031,beacon,astra,gumby,hammond,hassan,757575,585858,chillin,fuck1,sander,lowell,radiohea,upyours,trek,courage,coolcool,classics,choochoo,darryl,nikki1,nitro,bugs,boytoy,ellen,excite,kirsty,kane,wingnut,wireless,icu812,1master,beatle,bigblock,blanca,wolfen,summer99,sugar1,tartar,sexysexy,senna,sexman,sick,someone,soprano,pippin,platypus,pixies,telephon,land,laura1,laurent,rimmer,road,report,1020,12qwaszx,arturo,around,hamish,halifax,fishhead,forum,dododo,doit,outside,paramedi,lonesome,mandy1,twist,uuuuu,uranus,ttttt,butcher,bruce1,helper,hopeful,eduard,dusty1,kathy1,katherin,moonbeam,muscles,monster1,monkeybo,morton,windsurf,vvvvvvv,vivid,install,1947,187187,1941,1952,tatiana,susan1,31415926,sinned,sexxy,senator,sebastian,shadows,smoothie,snowflak,playstat,playa,playboy1,toaster,jerry1,marie1,mason1,merlin1,roger1,roadster,112358,1121,andrea1,bacardi,auto,hardware,hardy,789789,5555555,captain1,flores,fergus,sascha,rrrrrrr,dome,onion,nutter,lololo,qqqqqqq,quick,undertak,uuuuuuuu,uuuuuuu,criminal,cobain,cindy1,coors,dani,descent,nimbus,nomad,nanook,norwich,bomb,bombay,broker,hookup,kiwi,winners,jackpot,1a2b3c4d,1776,beardog,bighead,blast,bird33,0987,stress,shot,spooge,pelican,peepee,perry,pointer,titan,thedoors,jeremy1,annabell,altima,baba,hallie,hate,hardone,5454,candace,catwoman,flip,faithful,finance,farmboy,farscape,genesis1,salomon,destroy,papers,option,page,loser1,lopez,r2d2,pumpkins,training,chriss,cumcum,ninjas,ninja1,hung,erika,eduardo,killers,miller1,islander,jamesbond,intel,jarvis,19841984,2626,bizzare,blue12,biker,yoyoma,sushi,styles,shitface,series,shanti,spanker,steffi,smart,sphinx,please1,paulie,pistons,tiburon,limited,maxwell1,mdogg,rockies,armstron,alexia,arlene,alejandr,arctic,banger,audio,asimov,augustus,grandpa,753951,4you,chilly,care1839,chapman,flyfish,fantasia,freefall,santa,sandrine,oreo,ohshit,macbeth,madcat,loveya,mallory,rage,quentin,qwerqwer,project,ramirez,colnago,citizen,chocha,cobalt,crystal1,dabears,nevets,nineinch,broncos1,helene,huge,edgar,epsilon,easter,kestrel,moron,virgil,winston1,warrior1,iiiiiiii,iloveyou2,1616,beat,bettina,woowoo,zander,straight,shower,sloppy,specialk,tinkerbe,jellybea,reader,romero,redsox1,ride,1215,1112,annika,arcadia,answer,baggio,base,guido,555666,carmel,cayman,cbr900rr,chips,gabriell,gertrude,glennwei,roxy,sausages,disco,pass1,luna,lovebug,macmac,queenie,puffin,vanguard,trip,trinitro,airwolf,abbott,aaa111,cocaine,cisco,cottage,dayton,deadly,datsun,bricks,bumper,eldorado,kidrock,wizard1,whiskers,wind,wildwood,istheman,interest,italy,25802580,benoit,bigones,woodland,wolfpac,strawber,suicide,3030,sheba1,sixpack,peace1,physics,pearson,tigger2,toad,megan1,meow,ringo,roll,amsterdam,717171,686868,5424,catherine,canuck,football1,footjob,fulham,seagull,orgy,lobo,mancity,truth,trace,vancouve,vauxhall,acidburn,derf,myspace1,boozer,buttercu,howell,hola,easton,minemine,munch,jared,1dragon,biology,bestbuy,bigpoppa,blackout,blowfish,bmw325,bigbob,stream,talisman,tazz,sundevil,3333333,skate,shutup,shanghai,shop,spencer1,slowhand,polish,pinky1,tootie,thecrow,leroy,jonathon,jubilee,jingle,martine,matrix1,manowar,michaels,messiah,mclaren,resident,reilly,redbaron,rollins,romans,return,rivera,andromed,athlon,beach1,badgers,guitars,harald,harddick,gotribe,6996,7grout,5wr2i7h8,635241,chase1,carver,charlotte,fallout,fiddle,fredrick,fenris,francesc,fortuna,ferguson,fairlane,felipe,felix1,forward,gasman,frost,fucks,sahara,sassy1,dogpound,dogbert,divx1,manila,loretta,priest,pornporn,quasar,venom,987987,access1,clippers,daylight,decker,daman,data,dentist,crusty,nathan1,nnnnnnnn,bruno1,bucks,brodie,budapest,kittens,kerouac,mother1,waldo1,wedding,whistler,whatwhat,wanderer,idontkno,1942,1946,bigdawg,bigpimp,zaqwsx,414141,3000gt,434343,shoes,serpent,starr,smurf,pasword,tommie,thisisit,lake,john1,robotics,redeye,rebelz,1011,alatam,asses,asians,bama,banzai,harvest,gonzalez,hair,hanson,575757,5329,cascade,chinese,fatty,fender1,flower2,funky,sambo,drummer1,dogcat,dottie,oedipus,osama,macleod,prozac,private1,rampage,punch,presley,concord,cook,cinema,cornwall,cleaner,christopher,ciccio,corinne,clutch,corvet07,daemon,bruiser,boiler,hjkl,eyes,egghead,expert,ethan,kasper,mordor,wasted,jamess,iverson3,bluesman,zouzou,090909,1002,switch,stone1,4040,sisters,sexo,shawna,smith1,sperma,sneaky,polska,thewho,terminat,krypton,lawson,library,lekker,jules,johnson1,johann,justus,rockie,romano,aspire,bastards,goodie,cheese1,fenway,fishon,fishin,fuckoff1,girls1,sawyer,dolores,desmond,duane,doomsday,pornking,ramones,rabbits,transit,aaaaa1,clock,delilah,noel,boyz,bookworm,bongo,bunnies,brady,buceta,highbury,henry1,heels,eastern,krissy,mischief,mopar,ministry,vienna,weston,wildone,vodka,jayson,bigbooty,beavis1,betsy,xxxxxx1,yogibear,000001,0815,zulu,420000,september,sigmar,sprout,stalin,peggy,patch,lkjhgfds,lagnaf,rolex,redfox,referee,123123123,1231,angus1,ariana,ballin,attila,hall,greedy,grunt,747474,carpedie,cecile,caramel,foxylady,field,gatorade,gidget,futbol,frosch,saiyan,schmidt,drums,donner,doggy1,drum,doudou,pack,pain,nutmeg,quebec,valdepen,trash,triple,tosser,tuscl,track,comfort,choke,comein,cola,deputy,deadpool,bremen,borders,bronson,break,hotass,hotmail1,eskimo,eggman,koko,kieran,katrin,kordell1,komodo,mone,munich,vvvvvvvv,winger,jaeger,ivan,jackson5,2222222,bergkamp,bennie,bigben,zanzibar,worm,xxx123,sunny1,373737,services,sheridan,slater,slayer1,snoop,stacie,peachy,thecure,times,little1,jennaj,marquis,middle,rasta69,1114,aries,havana,gratis,calgary,checkers,flanker,salope,dirty1,draco,dogface,luv2epus,rainbow6,qwerty123,umpire,turnip,vbnm,tucson,troll,aileen,codered,commande,damon,nana,neon,nico,nightwin,neil,boomer1").split(",");
        for (String commonPass : mostCommon) {
            dictionary.add(commonPass);
            for(int i = 0; i < unsolvedUsers.size(); i++){
                UserEntry user = unsolvedUsers.get(i);
                String crypto = jcrypt.crypt(user.salt, commonPass);
                if (crypto.equals(user.password)) {
                    System.out.println(commonPass);
                    unsolvedUsers.remove(i);
                }
            }
        }
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
                    System.out.println(namePermutation);
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
                                System.out.println(manglePermutation);
                                unsolvedUsers.remove(finalUserRef);
                                userIsSolved = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!userIsSolved){ // Doubling the permutations of previous strategy
                for (Object usersPermutation : userNamePermutations) {
                    String namePermutation = (String) usersPermutation;
                    for (Function mangle : manglePermutations) {
                        for (Function mangle2 : manglePermutations) {
                            if (!userIsSolved){
                                String doubleManglePermutation = (String) mangle.apply(mangle2.apply(namePermutation));
                                userManglePermutations.add(doubleManglePermutation);
                                String crypto = jcrypt.crypt(user.salt, doubleManglePermutation);
                                if (crypto.equals(user.password)) {
                                    System.out.println(doubleManglePermutation);
                                    unsolvedUsers.remove(finalUserRef);
                                    userIsSolved = true;
                                    break;
                                }
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
                                System.out.println(mangleCharPermutations);
                                unsolvedUsers.remove(finalUserRef);
                                userIsSolved = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void tryDictionaryVariants() {
        ArrayList dictionaryCombos = new ArrayList();
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        int wordsPerThread = dictionary.size()/numOfThreads;
        Thread[] threads = new Thread[numOfThreads];
        for (int t = 0; t < numOfThreads; t++) {
            int threadRef = t;
            threads[t] = new Thread(() -> {
                for (int i = (wordsPerThread* threadRef); i < (wordsPerThread*(threadRef +1)); i++) {
                    for (Function func : manglePermutations) {
                        String wordMangle = (String) func.apply(dictionary.get(i));
                        dictionaryCombos.add(wordMangle);
                        for (int j = 0; j < unsolvedUsers.size(); j++) {
                            UserEntry user = unsolvedUsers.get(j);
                            String crypto = jcrypt.crypt(user.salt, wordMangle);
                            if (crypto.equals(user.password)) {
                                System.out.println(wordMangle);
                                unsolvedUsers.remove(j);
                            }
                        }
                    }
                }
            });
        }
        for(Thread t : threads) t.start();
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int combosPerThread = dictionaryCombos.size()/numOfThreads;
        for (int t = 0; t < numOfThreads; t++) {
            int threadRef = t;
            threads[t] = new Thread(() -> {
                for (int i = (combosPerThread* threadRef); i < (combosPerThread*(threadRef +1)); i++) {
                    for (Function func : charPermutations) {
                        for (int asci = 48; asci < 57; asci++){
                            String charMangle = (String) func.apply(new String[]{(String)dictionaryCombos.get(i), String.valueOf((char)asci)});
                            for (int j = 0; j < unsolvedUsers.size(); j++) {
                                UserEntry user = unsolvedUsers.get(j);
                                String crypto = jcrypt.crypt(user.salt, charMangle);
                                if (crypto.equals(user.password)) {
                                    System.out.println(charMangle);
                                    unsolvedUsers.remove(j);
                                }
                            }
                        }
                    }
                }
            });
        }
        for(Thread t : threads) t.start();
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            for(int i = 0; i < stringArr.length; i++){ sb.append(i%2 == 0 ? stringArr[i].toUpperCase() : stringArr[i].toLowerCase()); }
            return sb.toString();
        };
        Function<String, String> toggleCaseLowerStart = (String input) -> {
            String[] stringArr = input.split("");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < stringArr.length; i++){ sb.append( i%2==0 ? stringArr[i].toLowerCase() : stringArr[i].toUpperCase()); }
            return sb.toString();
        };
        Function<String, String> removeFirst = (String input) -> input.substring(1,input.length());
        Function<String, String> removeLast = (String input) -> input.substring(0,input.length()-1);
        Function<String, String> reverse = (String input) -> {
            char[] charArr = input.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = charArr.length-1; i>=0; i--){ sb.append(charArr[i]); }
            return sb.toString();
        };
        Function<String, String> duplicate = (String input) -> input+input;
        Function<String, String> reflect = (String input) -> input + reverse.apply(input);
        Function<String, String> capitalizedReflect = (String input) -> capitalize.apply(input) + reverse.apply(capitalize.apply(input));
        Function<String, String> capitalizedReverseReflect = (String input) -> reverse.apply(capitalize.apply(input)) + capitalize.apply(input);
        Function<String, String> upperCaseReflect = (String input) -> upperCase.apply(input) + reverse.apply(upperCase.apply(input));
        Function<String, String> reversereflect = (String input) -> reverse.apply(input) + input;
        Function<String, String> toggleStartBigReflect = (String input) -> toggleCaseUpperStart.apply(input) + reflect.apply(toggleCaseUpperStart.apply(input));
        Function<String, String> toggleStartSmallReflect = (String input) -> toggleCaseLowerStart.apply(input) + reflect.apply(toggleCaseLowerStart.apply(input));

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
        manglePermutations.add(reflect);
        manglePermutations.add(capitalizedReflect);
        manglePermutations.add(capitalizedReverseReflect);
        manglePermutations.add(upperCaseReflect);
        manglePermutations.add(reversereflect);
        manglePermutations.add(toggleStartBigReflect);
        manglePermutations.add(toggleStartSmallReflect);
    }

    /**
     * Input[0] is first name, Input[1] is surname
     */
    private static void addNamePermutationFunctions() {
        Function<String[], String> firstOnly = (String[] input) -> input[0].toLowerCase();
        Function<String[], String> lastOnly = (String[] input) -> input[1].toLowerCase();
        Function<String[], String> bothOnly = (String[] input) -> input[0].toLowerCase() + input[1].toUpperCase();
        Function<String[], String> bothCapitalize = (String[] input) -> (String.valueOf(input[0].charAt(0)).toUpperCase() + input[0].substring(1,input[0].length()))
                + (String.valueOf(input[1].charAt(0)).toUpperCase() + input[1].substring(1,input[1].length()));
        Function<String, String> reverse = (String input) -> {
            char[] charArr = input.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = charArr.length-1; i>=0; i--){ sb.append(charArr[i]); }
            return sb.toString();
        };
        Function<String[], String> reflect = (String[] input) -> firstOnly.apply(input) + reverse.apply(lastOnly.apply(input));
        Function<String[], String> firstAppend123 = (String[] input) -> firstOnly.apply(input) + "123";
        Function<String[], String> laststAppend123 = (String[] input) -> lastOnly.apply(input) + "123";
        Function<String[], String> bothCapitalizedAppend = (String[] input) -> bothCapitalize.apply(input) + "123";

        namePermutations.add(firstOnly);
        namePermutations.add(lastOnly);
        namePermutations.add(lastOnly);
        namePermutations.add(bothCapitalize);
        namePermutations.add(bothOnly);
        namePermutations.add(reflect);
        namePermutations.add(firstAppend123);
        namePermutations.add(laststAppend123);
        namePermutations.add(bothCapitalizedAppend);
    }

    /**
     * Input[0] is word, Input[1] is char to append.
     */
    private static void addCharPermutationFunctions() {
        Function<String[], String> appendChar = (String[] input) -> input[0] + input[1];
        Function<String[], String> prependChar = (String[] input) -> input[1] + input[0];
        Function<String[], String> prependAndAppend = (String[] input) -> input[1] + input[0] + input[1];
        Function<String[], String> prependAndAppendCapitalized = (String[] input) -> input[1].toUpperCase() + input[0].toLowerCase() + input[1].toUpperCase();
        Function<String[], String> doubleAppendChar = (String[] input) -> input[0] + input[1] + input[1];
        Function<String[], String> doublePrependChar = (String[] input) -> input[1] + input[1] + input[0];
        charPermutations.add(appendChar);
        charPermutations.add(prependChar);
        charPermutations.add(prependAndAppend);
        charPermutations.add(prependAndAppendCapitalized);
        charPermutations.add(doubleAppendChar);
        charPermutations.add(doublePrependChar);
    }

    private static void loadUserInfo(String pswdFileName){
        if (pswdFileName == null || pswdFileName.equals("")) throw new IllegalArgumentException("Argument for Dictionary file is empty?");
        String basepath = new File("").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(basepath + "/src/" + pswdFileName)));
            String st;
            while ((st = br.readLine()) != null) {
                String[] parts = st.split(":");
                unsolvedUsers.add(new UserEntry(parts[1], parts[4]));
            }
        }catch (IndexOutOfBoundsException e){
            System.err.println("A line in the password file is not formatted correctly? Program gets 'Index out of bounds' after splitting on ':' accessing indexes.");
        }
        catch (FileNotFoundException e) {
            System.err.println("The file was not found. Make sure the file is in the same directory as PasswordCrack.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadDictionary(String dictionaryName) {
        if (dictionaryName == null || dictionaryName.equals("")) throw new IllegalArgumentException("Argument for Dictionary file is empty?");
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
        String firstName;
        String surName;

        UserEntry(String password, String fullName){
            this.password = password;
            this.salt = password.substring(0,2);
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
