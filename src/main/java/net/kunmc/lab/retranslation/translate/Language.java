package net.kunmc.lab.retranslation.translate;

public enum Language {
    AUTO("", "自動検出"),
    IS("is", "アイスランド語"),
    GA("ga", "アイルランド語"),
    AZ("az", "アゼルバイジャン語"),
    AF("af", "アフリカーンス語"),
    AM("am", "アムハラ語"),
    AR("ar", "アラビア語"),
    SQ("sq", "アルバニア語"),
    HY("hy", "アルメニア語"),
    IT("it", "イタリア語"),
    YI("yi", "イディッシュ語"),
    IG("ig", "イボ語"),
    ID("id", "インドネシア語"),
    UG("ug", "ウイグル語"),
    CY("cy", "ウェールズ語"),
    UK("uk", "ウクライナ語"),
    UZ("uz", "ウズベク語"),
    UR("ur", "ウルドゥ語"),
    ET("et", "エストニア語"),
    EO("eo", "エスペラント語"),
    NL("nl", "オランダ語"),
    OR("or", "オリヤ語"),
    KK("kk", "カザフ語"),
    CA("ca", "カタルーニャ語"),
    GL("gl", "ガリシア語"),
    KN("kn", "カンナダ語"),
    RW("rw", "キニヤルワンダ語"),
    EL("el", "ギリシャ語"),
    KY("ky", "キルギス語"),
    GU("gu", "グジャラト語"),
    KM("km", "クメール語"),
    KU("ku", "クルド語"),
    HR("hr", "クロアチア語"),
    XH("xh", "コーサ語"),
    CO("co", "コルシカ語"),
    SM("sm", "サモア語"),
    JW("jw", "ジャワ語"),
    KA("ka", "グルジア語"),
    SN("sn", "ショナ語"),
    SD("sd", "シンド語"),
    SI("si", "シンハラ語"),
    SV("sv", "スウェーデン語"),
    ZU("zu", "ズールー語"),
    GD("gd", "スコットランド・ゲール語"),
    ES("es", "スペイン語"),
    SK("sk", "スロバキア語"),
    SL("sl", "スロベニア語"),
    SW("sw", "スワヒリ語"),
    SU("su", "スンダ語"),
    CEB("ceb", "セブアノ語"),
    SR("sr", "セルビア語"),
    ST("st", "ソト語"),
    SO("so", "ソマリ語"),
    TH("th", "タイ語"),
    TL("tl", "タガログ語"),
    TG("tg", "タジク語"),
    TT("tt", "タタール語"),
    TA("ta", "タミル語"),
    CS("cs", "チェコ語"),
    NY("ny", "チェワ語"),
    TE("te", "テルグ語"),
    DA("da", "デンマーク語"),
    DE("de", "ドイツ語"),
    TK("tk", "トルクメン語"),
    TR("tr", "トルコ語"),
    NE("ne", "ネパール語"),
    NO("no", "ノルウェー語"),
    HT("ht", "ハイチ語"),
    HA("ha", "ハウサ語"),
    PS("ps", "パシュト語"),
    EU("eu", "バスク語"),
    HAW("haw", "ハワイ語"),
    HU("hu", "ハンガリー語"),
    PA("pa", "パンジャブ語"),
    HI("hi", "ヒンディー語"),
    FI("fi", "フィンランド語"),
    FR("fr", "フランス語"),
    FY("fy", "フリジア語"),
    BG("bg", "ブルガリア語"),
    VI("vi", "ベトナム語"),
    IW("iw", "ヘブライ語"),
    BE("be", "ベラルーシ語"),
    FA("fa", "ペルシャ語"),
    BN("bn", "ベンガル語"),
    PL("pl", "ポーランド語"),
    BS("bs", "ボスニア語"),
    PT("pt", "ポルトガル語"),
    MI("mi", "マオリ語"),
    MK("mk", "マケドニア語"),
    MR("mr", "マラーティー語"),
    MG("mg", "マラガシ語"),
    ML("ml", "マラヤーラム語"),
    MT("mt", "マルタ語"),
    MS("ms", "マレー語"),
    MY("my", "ミャンマー語"),
    MN("mn", "モンゴル語"),
    HMN("hmn", "モン語"),
    YO("yo", "ヨルバ語"),
    LO("lo", "ラオ語"),
    LA("la", "ラテン語"),
    LV("lv", "ラトビア語"),
    LT("lt", "リトアニア語"),
    RO("ro", "ルーマニア語"),
    LB("lb", "ルクセンブルク語"),
    RU("ru", "ロシア語"),
    EN("en", "英語"),
    KO("ko", "韓国語"),
    ZHCN("zh-CN", "中国語"),
    JA("ja", "日本語");

    private final String id;
    private final String name;

    Language(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getLanguageName() {
        return name;
    }

    public static Language getLanguageById(String id) {
        for (Language language : values()) {
            if (language.id.equals(id)) {
                return language;
            }
        }
        return null;
    }
}
