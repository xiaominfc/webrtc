package com.xiaominfc.apprtc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by xiaominfc on 15/09/2017.
 */

public class AppRTCManager {

    private Context mContext;
    private SharedPreferences sharedPref;

    private String keyprefVideoCallEnabled;
    private String keyprefScreencapture;
    private String keyprefCamera2;
    private String keyprefResolution;
    private String keyprefFps;
    private String keyprefCaptureQualitySlider;
    private String keyprefVideoBitrateType;
    private String keyprefVideoBitrateValue;
    private String keyprefVideoCodec;
    private String keyprefAudioBitrateType;
    private String keyprefAudioBitrateValue;
    private String keyprefAudioCodec;
    private String keyprefHwCodecAcceleration;
    private String keyprefCaptureToTexture;
    private String keyprefFlexfec;
    private String keyprefNoAudioProcessingPipeline;
    private String keyprefAecDump;
    private String keyprefOpenSLES;
    private String keyprefDisableBuiltInAec;
    private String keyprefDisableBuiltInAgc;
    private String keyprefDisableBuiltInNs;
    private String keyprefEnableLevelControl;
    private String keyprefDisableWebRtcAGCAndHPF;
    private String keyprefDisplayHud;
    private String keyprefTracing;
    private String keyprefRoomServerUrl;
    private String keyprefRoom;
    private String keyprefRoomList;
    private ArrayList<String> roomList;
    private ArrayAdapter<String> adapter;
    private String keyprefEnableDataChannel;
    private String keyprefOrdered;
    private String keyprefMaxRetransmitTimeMs;
    private String keyprefMaxRetransmits;
    private String keyprefDataProtocol;
    private String keyprefNegotiated;
    private String keyprefDataId;

    private static boolean commandLineRun = false;


    private static final AppRTCManager ourInstance = new AppRTCManager();

    public static AppRTCManager getInstance() {
        return ourInstance;
    }

    private AppRTCManager() {
    }

    public void init(Context context) {
        mContext = context;
        initContent();
    }

    private void initContent() {
        PreferenceManager.setDefaultValues(mContext, R.xml.preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        keyprefVideoCallEnabled = getString(R.string.pref_videocall_key);
        keyprefScreencapture = getString(R.string.pref_screencapture_key);
        keyprefCamera2 = getString(R.string.pref_camera2_key);
        keyprefResolution = getString(R.string.pref_resolution_key);
        keyprefFps = getString(R.string.pref_fps_key);
        keyprefCaptureQualitySlider = getString(R.string.pref_capturequalityslider_key);
        keyprefVideoBitrateType = getString(R.string.pref_maxvideobitrate_key);
        keyprefVideoBitrateValue = getString(R.string.pref_maxvideobitratevalue_key);
        keyprefVideoCodec = getString(R.string.pref_videocodec_key);
        keyprefHwCodecAcceleration = getString(R.string.pref_hwcodec_key);
        keyprefCaptureToTexture = getString(R.string.pref_capturetotexture_key);
        keyprefFlexfec = getString(R.string.pref_flexfec_key);
        keyprefAudioBitrateType = getString(R.string.pref_startaudiobitrate_key);
        keyprefAudioBitrateValue = getString(R.string.pref_startaudiobitratevalue_key);
        keyprefAudioCodec = getString(R.string.pref_audiocodec_key);
        keyprefNoAudioProcessingPipeline = getString(R.string.pref_noaudioprocessing_key);
        keyprefAecDump = getString(R.string.pref_aecdump_key);
        keyprefOpenSLES = getString(R.string.pref_opensles_key);
        keyprefDisableBuiltInAec = getString(R.string.pref_disable_built_in_aec_key);
        keyprefDisableBuiltInAgc = getString(R.string.pref_disable_built_in_agc_key);
        keyprefDisableBuiltInNs = getString(R.string.pref_disable_built_in_ns_key);
        keyprefEnableLevelControl = getString(R.string.pref_enable_level_control_key);
        keyprefDisableWebRtcAGCAndHPF = getString(R.string.pref_disable_webrtc_agc_and_hpf_key);
        keyprefDisplayHud = getString(R.string.pref_displayhud_key);
        keyprefTracing = getString(R.string.pref_tracing_key);
        keyprefRoomServerUrl = getString(R.string.pref_room_server_url_key);
        keyprefRoom = getString(R.string.pref_room_key);
        keyprefRoomList = getString(R.string.pref_room_list_key);
        keyprefEnableDataChannel = getString(R.string.pref_enable_datachannel_key);
        keyprefOrdered = getString(R.string.pref_ordered_key);
        keyprefMaxRetransmitTimeMs = getString(R.string.pref_max_retransmit_time_ms_key);
        keyprefMaxRetransmits = getString(R.string.pref_max_retransmits_key);
        keyprefDataProtocol = getString(R.string.pref_data_protocol_key);
        keyprefNegotiated = getString(R.string.pref_negotiated_key);
        keyprefDataId = getString(R.string.pref_data_id_key);
    }


    private String getString(int id) {
        return mContext.getString(id);
    }

    public String getRoom() {
        return  sharedPref.getString(keyprefRoom, "");
    }

    public String getRoomList() {
        return sharedPref.getString(keyprefRoomList, "");
    }

    public void putRoomAndRoomList(String room,String roomListJson) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(keyprefRoom, room);
        editor.putString(keyprefRoomList, roomListJson);
        editor.commit();
    }

    public String getString(String attributeName, String defaultValue) {
        return sharedPref.getString(attributeName, defaultValue);
    }

    public boolean getBoolean(String attributeName, boolean defaultValue) {
        return sharedPref.getBoolean(attributeName, defaultValue);
    }

    public String getResolution(){
        return sharedPref.getString(keyprefResolution, getString(R.string.pref_resolution_default));
    }

    public String getRoomServerUrl() {
        return sharedPref.getString(
                keyprefRoomServerUrl, getString(R.string.pref_room_server_url_default));
    }

    public int getVideoStartBitrate(){
        String bitrateTypeDefault = getString(R.string.pref_maxvideobitrate_default);
        String bitrateType = sharedPref.getString(keyprefVideoBitrateType, bitrateTypeDefault);
        if (!bitrateType.equals(bitrateTypeDefault)) {
            String bitrateValue = sharedPref.getString(
                    keyprefVideoBitrateValue, getString(R.string.pref_maxvideobitratevalue_default));
            return Integer.parseInt(bitrateValue);
        }
        return 0;
    }

    public int getAudioStartBitrate(){
        String bitrateTypeDefault = getString(R.string.pref_startaudiobitrate_default);
        String bitrateType = sharedPref.getString(keyprefAudioBitrateType, bitrateTypeDefault);
        if (!bitrateType.equals(bitrateTypeDefault)) {
            String bitrateValue = sharedPref.getString(
                    keyprefAudioBitrateValue, getString(R.string.pref_startaudiobitratevalue_default));
            return Integer.parseInt(bitrateValue);
        }
        return 0;
    }

    public int getCameraFps(){
        int cameraFps = 0;
        String fps = sharedPref.getString(keyprefFps, getString(R.string.pref_fps_default));
        String[] fpsValues = fps.split("[ x]+");
        if (fpsValues.length == 2) {
            try {
                cameraFps = Integer.parseInt(fpsValues[0]);
            } catch (NumberFormatException e) {
                //Log.e(TAG, "Wrong camera fps setting: " + fps);
            }
        }

        return cameraFps;
    }
}
