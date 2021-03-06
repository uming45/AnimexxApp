package de.meisterfuu.animexx.objects.rpg;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import de.meisterfuu.animexx.objects.UserObject;

public class RPGObject implements Comparable<RPGObject> {

    //		  "id":425985,
    @SerializedName("id")
    long id;


    //		  "name":"Tofu-Test-RPG",
    @SerializedName("name")
    String name;

    //		  "postings":11611,
    @SerializedName("postings")
    int postCount;

    //		  "beendet":false,
    @SerializedName("beendet")
    boolean finished;

//		  "letzt_datum_server":"2013-04-13 16:29:38",
//		  "letzt_datum_utc":"2013-04-13 14:29:38",
    @SerializedName("letzt_datum_utc")
    String lastPostDate;

    @SerializedName("letzt_datum_server")
    String lastPostDateServer;

    //		  "letzt_charakter":"",
    @SerializedName("letzt_charakter")
    String lastCharacter;

    //		  "letzt_spieler":"",
    @SerializedName("letzt_spieler")
    String lastPlayer;

    //		  "offen":false,
    @SerializedName("offen")
    boolean open;

    //		  "offen_charaktere":true,
    @SerializedName("offen_charaktere")
    boolean openCharacters;

    //		  "thema":0,
    @SerializedName("thema")
    int topic;

    //		  "thema_name":"",
    @SerializedName("thema_name")
    String topicName;

//		  "posting_metadaten":[
//		    "Ort",
//		    "Zeit",
//		    "Test-Angabe",
//		    "<\\'>\":\/"
//		  ],	
    @SerializedName("posting_metadaten")
    ArrayList<String> metadata;


    //		  "frei":3,
    @SerializedName("frei")
    int free;

    //		  "tofu":true,
    @SerializedName("tofu")
    boolean tofu;

//		  "tofu_data":{
//		    "id":30867,
//		    "mitglied": { ... }, User-Objekt
//		    "datum_start":"2012-05-09 13:39:39", Wann wurde das RPG vertofuziert
//		    "datum_ende":null, null, falls das Abo automatisch verlängert wird; ansonsten das Ende des Abos.
//		    "zahlung_next":"2013-12-06", Wann die nächste Zahlung erfolgt; oder null, falls keine nächste Zahlung mehr erfolgt
//		    "restzeit":2, Tage bis Ende / Nächste Zahlung
//		    "storniert":false Ob es storniert wurde
//		  },

//		  "tofu_at_hide":0, Bitmaske über die BBCodes, die nicht akzeptiert werden; aktuell noch nicht genauer dokumentiert
//		  "tofu_avatare":3, Position der Avatare; aktuell noch nicht genauer dokumentiert

//		  "tofu_sponsor":{ ... }, User-Objekt; sollte identisch mit tofu_data["mitglied"] sein
    @SerializedName("tofu_sponsor")
    UserObject tofuSponsor;

//		  "tofu_post_editable":2, Können Posting nachträglich bearbeitet werden; 0 == Nein, 1 == Bis max. 3 Tage nach dem Posting; 2 == Ja, immer.
    @SerializedName("tofu_post_editable")
    int tofuPostEditable;

//		  "tofu_justify":true, Blocksatz bei den Postings?
//		  "tofu_first_letter":0, Name der Zierschrift-Initialen-Schriftart
//		  "posts_pro_seite":30,


    //		  "sprache":"de",
    @SerializedName("sprache")
    String language;

//		  "typ_genre":0,
//		  "typ_hentai":0,
//		  "typ_geschlecht":0,
//		  "typ_zulassung":0,
//		  "typ_hauptzeit":0,
//		  "typ_schreibstil":0,


    //	      "typ_spielerzahl":0,
    @SerializedName("typ_spielerzahl")
    int playerCount;


    //		  "adult":false,
    @SerializedName("adult")
    boolean adult;

//		  "neuespieler":1,
//		  "spieler_ticker":1,

    //		  "spieler":[
//		      Spieler/Charakter-Objekte
//		  ],
    @SerializedName("spieler")
    ArrayList<PlayerObject> player;

//		  "beschreibungen":[
//		      Beschreibungsseiten-Objekte
//		  ]
    @SerializedName("beschreibungen")
    ArrayList<DescriptionPageObject> descriptionPages;

    public RPGObject() {

    }

    public String getLastPostDateServer() {
        return lastPostDateServer;
    }

    public void setLastPostDateServer(String lastPostDateServer) {
        this.lastPostDateServer = lastPostDateServer;
    }

    public ArrayList<String> getMetadata() {
        return metadata;
    }

    public void setMetadata(ArrayList<String> metadata) {
        this.metadata = metadata;
    }

    public UserObject getTofuSponsor() {
        return tofuSponsor;
    }

    public void setTofuSponsor(UserObject tofuSponsor) {
        this.tofuSponsor = tofuSponsor;
    }

    public ArrayList<DescriptionPageObject> getDescriptionPages() {
        return descriptionPages;
    }

    public void setDescriptionPages(ArrayList<DescriptionPageObject> descriptionPages) {
        this.descriptionPages = descriptionPages;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getLastPostDate() {
        if (lastPostDate == null) {
            return "0";
        }
        return lastPostDate;
    }

    public void setLastPostDate(String lastPostDate) {
        this.lastPostDate = lastPostDate;
    }

    public String getLastCharacter() {
        return lastCharacter;
    }

    public void setLastCharacter(String lastCharacter) {
        this.lastCharacter = lastCharacter;
    }

    public String getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(String lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpenCharacters() {
        return openCharacters;
    }

    public void setOpenCharacters(boolean openCharacters) {
        this.openCharacters = openCharacters;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public boolean isTofu() {
        return tofu;
    }

    public void setTofu(boolean tofu) {
        this.tofu = tofu;
    }

    public int getTofuPostEditable() {
        return tofuPostEditable;
    }

    public void setTofuPostEditable(int tofuPostEditable) {
        this.tofuPostEditable = tofuPostEditable;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }


    public ArrayList<PlayerObject> getPlayer() {
        return player;
    }

    public void setPlayer(ArrayList<PlayerObject> player) {
        this.player = player;
    }


    public static class PlayerObject {


        //	{
//		  "id":6743743,
        @SerializedName("id")
        long id;

        //		  "rpg":536169,
        @SerializedName("rpg")
        long rpgId;

        //		  "charakter":"Testcharakter2", Name
        @SerializedName("charakter")
        String characterName;

        //		  "mitglied":null, User-Objekt oder null, falls nicht besetzt
        @SerializedName("mitglied")
        UserObject user;

        //		  "admin":false, true = Moderator-Rechte
        @SerializedName("admin")
        boolean admin;

        //		  "stelle":0, Zur Sortierung bei der Anzeige
        @SerializedName("stelle")
        int orderNumber;

        //		  "status":1,
//		    -1 = Nicht besetzt, keine Bewerbung möglich. "mitglied" ist dann immer null.
//		     0 = Aktiv; "mitglied" ist ein User-Objekt
//		     1 = Nicht besetzt, Bewerbungen möglich. "mitglied" ist dann immer null
//		     2 = Das unter "mitglied" angegebene Mitglied wurde eingeladen
        @SerializedName("status")
        int status;

        public static final int STATUS_CLOSED = -1;
        public static final int STATUS_OPEN = 1;
        public static final int STATUS_INVITED = 2;
        public static final int STATUS_ACTIVE = 0;

//		  "avatare":[
//		    {
//		      "id":1450427, Avatar-ID
//		      "x":160, Breite des Avatars
//		      "y":120  H�he des Avatars
//		    },
//		    {
//		      "id":1830921,
//		      "x":160,
//		      "y":160
//		    }
//		  ],
        @SerializedName("avatare")
        ArrayList<PlayerAvatarObject> avatars;


//		  "eigenschaften":[
//		    [
//		     "Bli",
//		     "bla" Ergibt in der Anzeige: "Bli: Bla"
//		   ],
//		   [
//		     "<a target='_blank' rel='nofollow' href=\"http:\/\/www.animexx.de\/\">Test2<\/a>",
//			 "Blubbw"
//		   ]
//		  ],
        @SerializedName("eigenschaften")
        ArrayList<ArrayList<String>> properties;



//		  "beschreibung":"Test<br>\nsdf",
        @SerializedName("beschreibung")
        String description;

//		  "hauptbild":"2012-11-21 17:02:08", UTC-Zeitpunkt des Uploads des Bildes; null, falls kein Bild vorhanden ist
        @SerializedName("hauptbild")
        String mainPicture;

//		  "hauptbild_x":129,
        @SerializedName("hauptbild_x")
        int mainPictureX;
//		  "hauptbild_y":200
        @SerializedName("hauptbild_y")
        int mainPictureY;

        @SerializedName("hauptbild_url")
        String mainPictureUrl;


        public PlayerObject() {

        }

        public ArrayList<ArrayList<String>> getProperties() {
            return properties;
        }

        public void setProperties(ArrayList<ArrayList<String>> properties) {
            this.properties = properties;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMainPicture() {
            return mainPicture;
        }

        public void setMainPicture(String mainPicture) {
            this.mainPicture = mainPicture;
        }

        public int getMainPictureX() {
            return mainPictureX;
        }

        public void setMainPictureX(int mainPictureX) {
            this.mainPictureX = mainPictureX;
        }

        public int getMainPictureY() {
            return mainPictureY;
        }

        public void setMainPictureY(int mainPictureY) {
            this.mainPictureY = mainPictureY;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getRpgId() {
            return rpgId;
        }

        public void setRpgId(long rpgId) {
            this.rpgId = rpgId;
        }

        public String getCharacterName() {
            return characterName;
        }

        public void setCharacterName(String characterName) {
            this.characterName = characterName;
        }

        public UserObject getUser() {
            return user;
        }

        public void setUser(UserObject user) {
            this.user = user;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ArrayList<PlayerAvatarObject> getAvatars() {
            return avatars;
        }

        public void setAvatars(ArrayList<PlayerAvatarObject> avatars) {
            this.avatars = avatars;
        }

        public String getMainPictureUrl() {
            return mainPictureUrl;
        }

        public void setMainPictureUrl(String mainPictureUrl) {
            this.mainPictureUrl = mainPictureUrl;
        }
    }

    public static class PlayerAvatarObject {


//	      "id":1450427, Avatar-ID
//	      "x":160, Breite des Avatars
//	      "y":120  Höhe des Avatars

//		media.animexx.onlinewelten.com/rpgs/charaktere/85/RPG-Id/charakterID_avatarID.jpg 
//		85 ist RPG-ID Modulo 100

        @SerializedName("id")
        long id;
        @SerializedName("x")
        int x;
        @SerializedName("y")
        int y;


        public PlayerAvatarObject() {
            id = -1;
            x = 0;
            y = 0;
        }

        public long getId() {
            return id;
        }


        public void setId(long id) {
            this.id = id;
        }


        public int getX() {
            return x;
        }


        public void setX(int x) {
            this.x = x;
        }


        public int getY() {
            return y;
        }


        public void setY(int y) {
            this.y = y;
        }


    }

    public static class DescriptionPageObject{

//        "id":376689,
        @SerializedName("id")
        long id;
//        "seite":1, Zur Sortierung der Seiten
        @SerializedName("seite")
        int page;
//        "name":"Seitentitel",
        @SerializedName("name")
        String name;
//            "orig_html":false,
        @SerializedName("orig_html")
        boolean wasHtml;
//            "text_html":"Test<br>\nasdsdf",
        @SerializedName("text_html")
        String html;
//            "datum_utc":"2013-11-15 12:56:52",
        @SerializedName("datum_utc")
        String dateUtc;
//            "datum_server":"2013-11-15 13:56:52",
        @SerializedName("datum_server")
        String dateServer;
//            "verfasser":null, null oder User-Objekt
        @SerializedName("verfasser")
        UserObject author;
//        "zugriff":0
        @SerializedName("zugriff")
        int rights;

        public static final int EVERYONE = 0;      //        0 == Alle Mitglieder dürfen die Seite sehen
        public static final int PLAYER = 1;      //        1 == Nur Mitspieler dürfen die Seite sehen
        public static final int MODS = 2;      //        2 == Nur RPG-Moderatoren dürfen die Seite sehen (noch nicht implementiert)

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getName() {
            if(name == null || name.isEmpty()){
                return "Seite";
            }
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isWasHtml() {
            return wasHtml;
        }

        public void setWasHtml(boolean wasHtml) {
            this.wasHtml = wasHtml;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getDateUtc() {
            return dateUtc;
        }

        public void setDateUtc(String dateUtc) {
            this.dateUtc = dateUtc;
        }

        public String getDateServer() {
            return dateServer;
        }

        public void setDateServer(String dateServer) {
            this.dateServer = dateServer;
        }

        public UserObject getAuthor() {
            return author;
        }

        public void setAuthor(UserObject author) {
            this.author = author;
        }

        public int getRights() {
            return rights;
        }

        public void setRights(int rights) {
            this.rights = rights;
        }
    }

    @Override
    public int compareTo(RPGObject another) {
          return -1 * this.getLastPostDate().compareTo(another.getLastPostDate());
    }
}
