package com.gemvietnam;

/**
 * Common constants
 * Created by neo on 7/18/2016.
 */
public class Constants {

  public static final String IS_NOT_FIRST_TIME_INSTALL = "first_session";
  public static final String LOGIN_AUTO = "login_auto";
  public static final String LOGIN_MANUAL = "login_manual";
  public static final String REGISTER = "register";
  public static final String TAG_LOG = "LOG_TRACKING";
  public static final String MESSAGE_TRACKING_LOG = "Firebase tracking: ";
  public static final String EXTRA_DATA = "extra_data";
  public static final String PACKAGE_NAME = "package_name";
  public static final String LOGIN_MANUAL_SUCCESS = "login_manual_success";
  public static final String LOGIN_AUTO_SUCCESS = "login_auto_success";
  public static final String REGISTER_FAIL = "register_fail";
  public static final String SERVICE_HOT = "service_hot";
  public static final String SERVICE_NORMAL = "service_normal";
  public static final String TOP_UP = "top_up";
  public static final String TOP_UP_CARD = "top_up_card";
  public static final String TOP_UP_BANK_PLUS = "top_up_bank_plus";
  public static final String TOP_UP_PAYVN = "top_up_payvn";
  public static final String TOP_UP_CARD_FAIL = "top_up_card_fail";
  public static final String PAYMENT = "payment";
  public static final String PAYMENT_CARD = "payment_card";
  public static final String PAYMENT_BANK_PLUS = "payment_bank_plus";
  public static final String PAYMENT_PAYVN = "payment_payvn";
  public static final String BILLING_HISTORY = "billing_history";
  public static final String BILLING_HISTORY_TAB_TRA_CUOC = "billing_history_tab_tra_cuoc";
  public static final String BILLING_HISTORY_TRA_CUOC = "billing_history_tra_cuoc";
  public static final String SESSION_END = "session_end";
  public static final String AVERAGE_SESSION_TIME = "average_session_time";
  public static final String LOGIN_SCREEN = "login_screen";
  public static final String HOME_SCREEN = "home_screen";
  public static final String TOP_UP_SCREEN = "top_up_screen";
  public static final String SERVICE_SCREEN = "service_screen";
  public static final String BILLING_HISTORY_SCREEN = "billing_history_screen";
  public static final String CSKH_SCREEN = "cskh_screen";
  public static final String LOGIN = "login";
  public static final String REGISTER_PROMOTION = "register_promotion";
  public static final String PROMOTION_CODE = "promotion_code";
  public static final String PROMOTION_NAME = "promotion_name";
  public static final int TYPE_CALL_SMS = 0;
  public static final int TYPE_DATA = 1;
  public static final int TYPE_DATA_EXTRA = 3;
  public static final String REGISTER_PROMOTION_DATA="register_promotion_data";
  public static final String PACK_NAME = "pack_name";
  public static final String PACK_CODE = "pack_code";
  public static final int RESTART_MODEM = 1;
  public static final int OFF_WIFI = 3;
  public static final int ON_WIFI = 2;
  public static final int CHANGE_PASS = 4;
  public static final String MODEM = "modem_object";
  public static final int MULTI_ACCOUNT_ADD_METHOD_INFO_OTP = 1;
  public static final int MULTI_ACCOUNT_ADD_METHOD_INFO_CODE = 2;
  public static final int MULTI_ACCOUNT_ADD_METHOD_INFO_CUSTOMER = 3;
  public static final String FLAG_M_WIFI = "FLAG_M_WIFI";

  public static final int TYPE_PROVINCE = 1;
  public static final int TYPE_DISTRICT = 2;
  public static final int TYPE_COMMUNE = 3;
  public static final int TYPE_VILLAGE = 4;
  //bo sung to/thon
  public static final int TYPE_HAMLET = 4;
  public static final int CREATE = 0;
  public static final int DANG_XY_LY = 1;
  public static final int DA_HUY = 4;
  public static final int DANG_XU_LY2 = 2;

  public static final String DA_XONG = "other";
  public static final int CHO_DAT_LICH_HEN = 2;

  public static final String TINH = "Tỉnh/Thành phố";
  public static final String HUYEN = "Quận/Huyện";
  public static final String XA = "Phường/Xã";
  public static final int CODE_REQUEST_PROVINCE = 001;
  public static final int CODE_REQUEST_DISTRICT = 002;
  public static final int CODE_REQUEST_PRECINCT = 003;
  public static final String TINH_ID = "Tinh ID";
  public static final String HUYEN_ID = "Huyen ID";
  public static final String CURRENT_TINH_ID = "Current Tinh";
  public static final String CURRENT_HUYEN_ID = "Current Huyen";
  public static final String CURRENT_XA_ID = "Current Xa";
  public static final String ADDRESS = "Address";
  public static final String MESSAGE = "create_message";

  public static final String COMPLAINT_ITEM = "complaint item";
  public static final String KEY_ID_COMPLAINT = "KEY_ID_COMPLAINT";

  public static final String DATA_ANALYTICS_PRIMARY_KEY = "DATA_ANALYTICS_PRIMARY_KEY";
  public static final String GET_DATA_ANALYTICS_ERROR_DETAIL_PRIMARY_KEY = "account";
  public static final String LST_ERROR_DETAIL = "LST_ERROR_DETAIL";
  // Report service
  public static final String REPORT_SERVICE_ACCOUNT_SELECTED = "REPORT_SERVICE_ACCOUNT_SELECTED";
  public static final String REPORT_SERVICE_TYPE = "REPORT_SERVICE_TYPE";
  public static final String REPORT_SERVICE_ERROR_GROUP_REFLECT = "REPORT_SERVICE_ERROR_GROUP_REFLECT";
  public static final String REPORT_SERVICE_ERROR_LIST_TYPE_COMPLAINT = "REPORT_SERVICE_ERROR_LIST_TYPE_COMPLAINT";
  public static final String REPORT_SERVICE_ERROR_LIST_WORD_BLOCKED = "REPORT_SERVICE_ERROR_LIST_WORD_BLOCKED";
  public static final String REPORT_SERVICE_ERROR_NAME_REFLECT = "REPORT_SERVICE_ERROR_NAME_REFLECT";
  public static final String REPORT_SERVICE_ERROR_NAME_STATUS = "REPORT_SERVICE_ERROR_NAME_STATUS";
  public static final String REPORT_SERVICE_ERROR_ACCOUNT_REPORT = "REPORT_SERVICE_ERROR_ACCOUNT_REPORT";
    public static final String REPORT_ERRORCODE = "REPORT_ERRORCODE";
  public static final int REPORT_SERVICE_TYPE_AUTO = 0;
  public static final int REPORT_SERVICE_TYPE_ERROR = 1;
  public static final int REPORT_SERVICE_TYPE_COMPLAINT = 2;
  public static final String ACCOUNT_RANK="account_rank";
  public static final String DATA_DESCRIPTION_ERROR = "data_description_error";
  public static final String DATA_DESCRIPTION_ERROR_COUNT = "DATA_DESCRIPTION_ERROR_COUNT";
  public static final String DATA_LIST_COMPLAIN = "list_complain";
  public static final String DATA_DESCRIPTION_ERROR_CODE = "DATA_DESCRIPTION_ERROR_CODE";
  public static final String DATA_INPUT_TEMPLATE = "DATA_INPUT_TEMPLATE";
  public static final String SEARCH_ERROR_SERVICE = "SEARCH_ERROR_SERVICE";
  public static final String DATA_CHOOSE_ACCOUNT_ERROR_CODE = "DATA_CHOOSE_ACCOUNT_ERROR_CODE";
    public static final String CONG_THANH_TOAN = "cong_thanh_toan";
    public static final String CONG_THANH_TOAN_NAP_TIEN = "cong_thanh_toan_nap_tien";
    public static final String COME_FORM_CHATBOT = "comeFromChatBot";
    public static final String COMPLAINT_CONTENT_CHATBOT = "complaint_content_chatbot";
    public static final String COMPLAINT_GROUP_TYPE_CHATBOT = "complaint_group_type_chatbot";
    public static final String COMPLAINT_SUBJECT_CHATBOT = "complaint_subject_chatbot";
    public static final String COME_FORM_CHATBOT_TO_CHANGESIM = "comeFromChatBotToChangeSim";
    public static final String COME_FORM_CHATBOT_TO_MAINSHOP = "comeFromChatBotToMainShop";

    public static String LayKetQuaPhanTichLoiCD = "layketquaphantichloicd";
  public static String isComplaintFirstTime = "is_first_time_tao_phieu";

  public static final int CHECK_SERVICE_CHECK = 0;
  public static final int CHECK_SERVICE_SEARCH = 1;
  public static final int CHECK_SERVICE_CREATE_TICKET = 2;

  //log firebase vtid
  public static final String VTID_DOI_CUOC = "vtid_udvt_doiCuoc";
  public static final String VTID_DOITIEN_VTPAY = "vtid_udvt_doiTienViettelPay";
  public static final String VTID_DOI_SMS = "vtid_udvt_doiSMS";
  public static final String VTID_DOI_DATA = "vtid_udvt_doiData";
  public static final String VTID_DOI_PHUTGOI = "vtid_udvt_doiPhutGoi";
  public static final String VTID_DOI_GOI_CUOC = "vtid_udvt_doiGoiCuoc";
  public static final String VTID_UUDAI_VUP = "vtid_udlk_uuDaiVip";
  public static final String VTID_AMTHUC = "vtid_udlk_amThuc";
  public static final String VTID_MUASAM = "vtid_udlk_muaSam";
  public static final String VTID_YTE_BAOHIEM = "vtid_udlk_yTeBaoHiem";
  public static final String VTID_DULICH_KHACHSAN = "vtid_udlk_duLichKhachSan";
  public static final String VTID_THETHAO_LAMDEP = "vtid_udlk_theThaoLamDep";
  public static final String VTID_GIAI_TRI = "vtid_udlk_giaiTri";
  public static final String VTID_GIAO_THONG = "vtid_udlk_giaoThong";
  public static final String VTID_GIAO_DUC = "vtid_udvt_doiCuoc";
  public static final String VTID_GIOI_THIEU_CHUNG = "vtid_gioiThieuChung";
  public static final String VTID_TIEUCHI_XETHANG = "vtid_tieuChiXetHang";
  public static final String VTID_QUYENLOI_HOIVIEN = "vtid_quyenLoiHoiVien";
  public static final String VTID_CHANGE_GOICUOC_SUCCESS = "vtid_change_GOICUOC_preferential_success";
  public static final String VTID_CHANGE_GOICUOC_FAILED = "vtid_change_GOICUOC_preferential_fail";
  public static final String SUCCESS = "success";

//  ===================== Key fireBaseQRCode ========================
  public static final String QR_CODE_NOT_LOGIN = "open_qrcode_not_login";
  public static final String QR_CODE_LOGIN = "open_qrcode_login";
  public static final String WEBVIEW_LIFEBOX = "webview_lifebox";
  public static final String SHARE_FACEBOOK = "qrcode_share_facebook";
  public static final String DOWNLOAD_FILE = "qrcode_download_file";
  public static final String COPPY_URL = "qrcode_coppy_url";

//  ===================== Accout type ========================
    public static final String ACCOUNT_TYPE_MOBILE = "1";
    public static final String ACCOUNT_TYPE_DCDOM = "3";
    public static final String ACCOUNT_TYPE_HP = "2";
    public static final String ACCOUNT_TYPE_ADSL = "A";
    public static final String ACCOUNT_TYPE_FTTH = "F";
    public static final String ACCOUNT_TYPE_NEXTTV = "I";
    public static final String ACCOUNT_TYPE_PSTN = "P";
    public static final String ACCOUNT_TYPE_MULTI = "U";
    public static final String ACCOUNT_TYPE_TRUYEN_HINH_CAP = "T";
    public static final String ACCOUNT_TYPE_NGN = "N";
    public static final String ACCOUNT_TYPE_TRUNG_KE = "K";
    public static final String ACCOUNT_TYPE_LEASELINE = "L";
    public static final String ACCOUNT_TYPE_EOC = "E";


//  ===================== Key fireBaseLifebox ========================
   public static final String LIFEBOX_REGISTER_CALL_API = "lifebox_register_call_api";
   public static final String LIFEBOX_REGISTER_SUCCESS = "lifebox_register_success";
   public static final String LIFEBOX_REGISTER_FAIL = "lifebox_register_fail";
   public static final String LIFEBOX_REGISTER_PACKAGE = "lifebox_register_package";
   public static final String LIFEBOX_REGISTER_PACKAGE_SUCCESS = "lifebox_register_package_success";
   public static final String LIFEBOX_REGISTER_PACKAGE_FAIL = "lifebox_register_package_fail";
   public static final String LIFEBOX_LOGIN_CALL_API = "lifebox_login_call_api";
   public static final String LIFEBOX_LOGIN_SUCCESS = "lifebox_login_success";
   public static final String LIFEBOX_LOGIN_FAIL = "lifebox_login_fail";
    //login auto
   public static final String LIFEBOX_AUTO_LOGIN_SUCCESS = "lifebox_auto_login_success";
   public static final String LIFEBOX_AUTO_LOGIN_FAIL = "lifebox_auto_login_fail";

   public static final String LIFEBOX_HOME_SCREEN = "lifebox_home_screen";
   public static final String LIFEBOX_PHOTO_SCREEN = "lifebox_photos_screen";
   public static final String LIFEBOX_VIDEOS_SCREEN = "lifebox_videos_screen";
   public static final String LIFEBOX_MUSICS_SCREEN = "lifebox_musics_screen";
    public static final String LIFEBOX_DOCUMENTS_SCREEN = "lifebox_documents_screen";
   public static final String LIFEBOX_LOCAL_PHOTOS_SCREEN = "lifebox_local_photos_screen";
   public static final String LIFEBOX_LOCAL_VIDEOS_SCREEN = "lifebox_local_videos_screen";
   public static final String LIFEBOX_LOCAL_MUSICS_SCREEN = "lifebox_local_musics_screen";
    public static final String LIFEBOX_LOCAL_DOCUMENTS_SCREEN = "lifebox_local_documents_screen";
   public static final String LIFEBOX_UPLOAD_PHOTO_SUCCESS = "lifebox_upload_photo_success";
   public static final String LIFEBOX_UPLOAD_PHOTO_FAIL = "lifebox_upload_photo_fail";
   public static final String LIFEBOX_UPLOAD_MUSIC_SUCCESS = "lifebox_upload_music_success";
   public static final String LIFEBOX_UPLOAD_MUSIC_FAIL = "lifebox_upload_music_fail";
    public static final String LIFEBOX_UPLOAD_DOCUMENT_SUCCESS = "lifebox_upload_document_success";
    public static final String LIFEBOX_UPLOAD_DOCUMENT_FAIL = "lifebox_upload_document_fail";
   public static final String LIFEBOX_UPLOAD_VIDEO_SUCCESS = "lifebox_upload_video_success";
   public static final String LIFEBOX_UPLOAD_VIDEO_FAIL = "lifebox_upload_video_fail";

    public static final String LIFEBOX_DOWNLOAD_PHOTO_SUCCESS = "lifebox_download_photo_success";
    public static final String LIFEBOX_DOWNLOAD_PHOTO_FAIL = "lifebox_download_photo_fail";
    public static final String LIFEBOX_DOWNLOAD_MUSIC_SUCCESS = "lifebox_download_music_success";
    public static final String LIFEBOX_DOWNLOAD_MUSIC_FAIL = "lifebox_download_music_fail";
    public static final String LIFEBOX_DOWNLOAD_DOCUMENT_SUCCESS = "lifebox_download_document_success";
    public static final String LIFEBOX_DOWNLOAD_DOCUMENT_FAIL = "lifebox_download_document_fail";
    public static final String LIFEBOX_DOWNLOAD_VIDEO_SUCCESS = "lifebox_download_video_success";
    public static final String LIFEBOX_DOWNLOAD_VIDEO_FAIL = "lifebox_download_video_fail";

   //  ===================== Key fireBase MyClip ========================
   public static final String MYCLIP_HOME_SCREEN = "giaitri_home_screen";
   public static final String MYCLIP_PLAY_VIDEO_SCREEN = "play_video";
   public static final String MYCLIP_PLAY_GAME_SCREEN = "play_game";
   public static final String MYCLIP_PLAY_VIDEO_MYCLIP_SCREEN = "giaitri_play_video_myclip_screen ";
   public static final String MYCLIP_PLAY_VIDEO_FILM_SCREEN = "giaitri_play_video_film_screen ";

   public static final String MYCLIP_MUSICS_SCREEN = "giaitri_musics_screen";
   public static final String MYCLIP_CLIPS_SCREEN = "giaitri_clips_screen";
   public static final String MYCLIP_GAMES_SCREEN = "giaitri_games_screen";
   public static final String MYCLIP_FILM_SCREEN = "giaitri_film_screen ";
   public static final int KEY_RESULT_FROM_GOOGLE_MAP = 1234;
   public static final String KEY_LOCATION = "KEY_LOCATION";
   public static final String KEY_LOCATION_DISTRICT = "KEY_LOCATION_DISTRICT";
   public static final String KEY_LOCATION_PROVINCE = "KEY_LOCATION_PROVINCE";
   public static final int KEY_RESULT_FROM_REPORT_ERROR_DETAIL = 4321;
   public static final int KEY_RESULT_FROM_REPORT_WIDE_AREA_ERROR = 4742;
    public static final int KEY_ERROR_IS_ANALYSTED = 100;
    public static final int KEY_ERROR_IS_NOT_ANALYSTED = 201;
    public static final String KEY_BACK_TO_SUPPORT_SERVICE = "KEY_BACK_TO_SUPPORT_SERVICE";
    public static final String LIST_SHOP_INFOR = "LIST_SHOP_INFOR";
    public static final String RESERVATION_INFOR = "RESERVATION_INFOR";
    public static final String ORDER_RESERVATIONS_ID = "ORDER_RESERVATIONS_ID";
    public static final String CODE_GETVOUCHER_INFOR = "CODE_GETVOUCHER_INFOR";
    public static final String SEND_LIST_WORD_BLOCKED = "SEND_LIST_WORD_BLOCKED";
    public static final String KEY_ACCOUNT_TYPE_MOBILE = "1";
    public static final String KEY_ACCOUNT_TYPE_HOME_PHONE = "2";

    public static final String INVITECODE = "invite_code";
    public static final String FLAG_DISPLAY_BUTTON_CLOSE_EDITTEXT = "display_button_close_edittext";

    // ===============Key esim ===========
    public static final String ALLOW_SERVICE = "ALLOW_SERVICE";
    public static final String ALLOW_COMMERCE = "ALLOW_COMMERCE";
    public static final String ALLOW_MARKET = "ALLOW_MARKET";
    public static final String TERMS = "terms";
    public static final String TURN_LEFT = "turn_left";
    public static final String TURN_RIGHT = "turn_right";
    public static final String BLINK = "blink";
    public static final String SMILE = "smile";

    // Insider
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String PROVIDER = "provider";
    public static final String AMOUNT = "amount";
    public static final String REASON = "reason";
    public static final String KEY_WORD = "keyword";

    // ==============MVTienIch==============
    public static final String MOBILE_TELECOMMUNICATION = "tien_ich_vien_thong";
    public static final String MOBILE_DISCOVERY = "tien_ich_kham_pha";
    public static final String LANDLINE_PHONE_PAY_BILL = "thanh_toan_cuoc";
    public static final String LANDLINE_PHONE_SERVICE_SUPPORT = "ho_tro_dich_vu";
    public static final String LANDLINE_PHONE_UTITLITIES = "tien_ich";
    public static final String LANDLINE_PHONE_DISCOVERY = "kham_pha";

    private Constants() {

  }
  public static final String EMPTY_STRING = "";
  public static final String LIST_GAME_SEARCH = "LIST_GAME_SEARCH";
  public static final String LIST_VIDEO_SEARCH = "LIST_VIDEO_SEARCH";
  public static final String LIST_PHIM_SEARCH = "LIST_PHIM_SEARCH";
    public static final String LIST_SHOP_SEARCH = "LIST_SHOP_SEARCH";
    public static final int NOT_CHECK_LOGIN_REQUIRE = -1000;
}
