/*
 * Copyright (C) 2020 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package device.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all permissions up until Android 11 (API 30, revision 3)
 * as found on the 6th of February 2021. Permissions that are part of another
 * permission, or those which are only usable by the system (meaning no
 * third-party should use them) are excluded from this class. The documentation
 * for each permission is directly taken from the Android developers website, as
 * linked in the author section below.
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl] and
 * Google's Android developers [see
 * https://developer.android.com/reference/android/Manifest.permission]
 */
public class Permissions {

    /**
     * Gets a list of all possible permissions up until Android 11 (API 30,
     * revision 3) as present on the 6th of February 2021
     *
     * @return all possible permissions
     */
    public static List<String> getAll() {
        List<String> permissions = new ArrayList<>();
        permissions.add("android.permission.BIND_TV_INPUT");
        permissions.add("android.permission.GET_ACCOUNTS");
        permissions.add("android.permission.READ_CONTACTS");
        permissions.add("android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        permissions.add("android.permission.CALL_PHONE");
        permissions.add("android.permission.SET_ALWAYS_FINISH");
        permissions.add("android.permission.BIND_QUICK_ACCESS_WALLET_SERVICE");
        permissions.add("android.permission.RESTART_PACKAGES");
        permissions.add("android.permission.INTERNET");
        permissions.add("android.permission.ACCESS_NOTIFICATION_POLICY");
        permissions.add("android.permission.TRANSMIT_IR");
        permissions.add("android.permission.SMS_FINANCIAL_TRANSACTIONS");
        permissions.add("android.permission.BIND_VOICE_INTERACTION");
        permissions.add("android.permission.USE_FULL_SCREEN_INTENT");
        permissions.add("android.permission.REQUEST_INSTALL_PACKAGES");
        permissions.add("android.permission.ACCESS_NETWORK_STATE");
        permissions.add("android.permission.PACKAGE_USAGE_STATS");
        permissions.add("android.permission.RECEIVE_WAP_PUSH");
        permissions.add("android.permission.READ_CALL_LOG");
        permissions.add("android.permission.REQUEST_DELETE_PACKAGES");
        permissions.add("android.permission.BIND_WALLPAPER");
        permissions.add("android.permission.QUERY_ALL_PACKAGES");
        permissions.add("android.permission.SET_WALLPAPER");
        permissions.add("android.permission.RECEIVE_BOOT_COMPLETED");
        permissions.add("android.permission.WAKE_LOCK");
        permissions.add("android.permission.LOADER_USAGE_STATS");
        permissions.add("android.permission.ACCESS_BACKGROUND_LOCATION");
        permissions.add("android.permission.USE_BIOMETRIC");
        permissions.add("android.permission.GET_ACCOUNTS_PRIVILEGED");
        permissions.add("android.permission.BIND_PRINT_SERVICE");
        permissions.add("android.permission.BLUETOOTH");
        permissions.add("android.permission.INSTANT_APP_FOREGROUND_SERVICE");
        permissions.add("android.permission.MANAGE_OWN_CALLS");
        permissions.add("android.permission.RECEIVE_MMS");
        permissions.add("android.permission.BIND_NFC_SERVICE");
        permissions.add("android.permission.CALL_PRIVILEGED");
        permissions.add("android.permission.FOREGROUND_SERVICE");
        permissions.add("android.permission.BROADCAST_STICKY");
        permissions.add("android.permission.BIND_SCREENING_SERVICE");
        permissions.add("android.permission.EXPAND_STATUS_BAR");
        permissions.add("GET_ACCOUNTS");
        permissions.add("android.permission.CALL_COMPANION_APP");
        permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
        permissions.add("android.permission.RECORD_AUDIO");
        permissions.add("android.permission.BIND_CARRIER_MESSAGING_CLIENT_SERVICE");
        permissions.add("android.permission.SYSTEM_ALERT_WINDOW");
        permissions.add("android.permission.REQUEST_PASSWORD_COMPLEXITY");
        permissions.add("android.permission.READ_SYNC_SETTINGS");
        permissions.add("android.permission.VIBRATE");
        permissions.add("android.permission.SET_PREFERRED_APPLICATIONS");
        permissions.add("android.permission.NFC");
        permissions.add("android.permission.ACCESS_COARSE_LOCATION");
        permissions.add("android.permission.ANSWER_PHONE_CALLS");
        permissions.add("android.permission.BIND_CARRIER_SERVICES");
        permissions.add("android.permission.BIND_INCALL_SERVICE");
        permissions.add("com.android.voicemail.permission.WRITE_VOICEMAIL");
        permissions.add("android.permission.NFC_TRANSACTION_EVENT");
        permissions.add("android.permission.BIND_ACCESSIBILITY_SERVICE");
        permissions.add("android.permission.START_VIEW_PERMISSION_USAGE");
        permissions.add("android.permission.READ_SYNC_STATS");
        permissions.add("android.permission.READ_EXTERNAL_STORAGE");
        permissions.add("android.permission.ACTIVITY_RECOGNITION");
        permissions.add("android.permission.ACCESS_CHECKIN_PROPERTIES");
        permissions.add("android.permission.BATTERY_STATS");
        permissions.add("android.permission.USE_FINGERPRINT");
        permissions.add("android.permission.BODY_SENSORS");
        permissions.add("android.permission.DISABLE_KEYGUARD");
        permissions.add("android.permission.WRITE_SETTINGS");
        permissions.add("android.permission.ACCESS_WIFI_STATE");
        permissions.add("android.permission.INTERACT_ACROSS_PROFILES");
        permissions.add("android.permission.USE_SIP");
        permissions.add("android.permission.PROCESS_OUTGOING_CALLS");
        permissions.add("android.permission.REORDER_TASKS");
        permissions.add("android.permission.BIND_CALL_REDIRECTION_SERVICE");
        permissions.add("android.permission.BIND_INPUT_METHOD");
        permissions.add("android.permission.BIND_DREAM_SERVICE");
        permissions.add("android.permission.KILL_BACKGROUND_PROCESSES");
        permissions.add("android.permission.BIND_AUTOFILL_SERVICE");
        permissions.add("android.permission.CHANGE_NETWORK_STATE");
        permissions.add("android.permission.GET_TASKS");
        permissions.add("android.permission.ACCEPT_HANDOVER");
        permissions.add("android.permission.CHANGE_CONFIGURATION");
        permissions.add("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS");
        permissions.add("android.permission.BIND_CONDITION_PROVIDER_SERVICE");
        permissions.add("android.permission.CAMERA");
        permissions.add("android.permission.PERSISTENT_ACTIVITY");
        permissions.add("com.android.launcher.permission.INSTALL_SHORTCUT");
        permissions.add("android.permission.ACCESS_FINE_LOCATION");
        permissions.add("android.permission.READ_SMS");
        permissions.add("android.permission.INSTALL_LOCATION_PROVIDER");
        permissions.add("android.permission.BIND_TEXT_SERVICE");
        permissions.add("android.permission.GLOBAL_SEARCH");
        permissions.add("android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND");
        permissions.add("android.permission.BIND_TELECOM_CONNECTION_SERVICE");
        permissions.add("android.permission.REBOOT");
        permissions.add("android.permission.BIND_QUICK_SETTINGS_TILE");
        permissions.add("android.permission.MANAGE_EXTERNAL_STORAGE");
        permissions.add("com.android.voicemail.permission.READ_VOICEMAIL");
        permissions.add("android.permission.RECEIVE_SMS");
        permissions.add("android.permission.BIND_MIDI_DEVICE_SERVICE");
        permissions.add("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE");
        permissions.add("android.permission.BIND_APPWIDGET");
        permissions.add("android.permission.SET_WALLPAPER_HINTS");
        permissions.add("android.permission.READ_PRECISE_PHONE_STATE");
        permissions.add("android.permission.SET_ANIMATION_SCALE");
        permissions.add("android.permission.WRITE_CALENDAR");
        permissions.add("android.permission.BIND_VR_LISTENER_SERVICE");
        permissions.add("android.permission.WRITE_CALL_LOG");
        permissions.add("android.permission.GET_PACKAGE_SIZE");
        permissions.add("android.permission.CHANGE_WIFI_STATE");
        permissions.add("android.permission.NFC_PREFERRED_PAYMENT_INFO");
        permissions.add("android.permission.BLUETOOTH_ADMIN");
        permissions.add("android.permission.READ_CALENDAR");
        permissions.add("android.permission.READ_PHONE_STATE");
        permissions.add("android.permission.ACCESS_MEDIA_LOCATION");
        permissions.add("android.permission.BIND_CHOOSER_TARGET_SERVICE");
        permissions.add("com.android.alarm.permission.SET_ALARM");
        permissions.add("android.permission.WRITE_SYNC_SETTINGS");
        permissions.add("com.android.voicemail.permission.ADD_VOICEMAIL");
        permissions.add("android.permission.BLUETOOTH_PRIVILEGED");
        permissions.add("android.permission.WRITE_CONTACTS");
        permissions.add("android.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND");
        permissions.add("android.permission.MODIFY_AUDIO_SETTINGS");
        permissions.add("android.permission.BIND_CARRIER_MESSAGING_SERVICE");
        permissions.add("android.permission.BIND_VPN_SERVICE");
        permissions.add("android.permission.DELETE_CACHE_FILES");
        permissions.add("android.permission.SEND_SMS");
        permissions.add("android.permission.CHANGE_WIFI_MULTICAST_STATE");
        permissions.add("android.permission.BIND_VISUAL_VOICEMAIL_SERVICE");
        permissions.add("android.permission.BIND_REMOTEVIEWS");
        permissions.add("android.permission.BIND_DEVICE_ADMIN");
        permissions.add("android.permission.CLEAR_APP_CACHE");
        return permissions;
    }

    /**
     * Allows a calling app to continue a call which was started in another app.
     * An example is a video calling app that wants to continue a voice call on
     * the user's mobile network.<br>
     * <br>
     * When the handover of a call from one app to another takes place, there
     * are two devices which are involved in the handover; the initiating and
     * receiving devices. The initiating device is where the request to handover
     * the call was started, and the receiving device is where the handover
     * request is confirmed by the other party.<br>
     * <br>
     * This permission protects access to the TelecomManager.acceptHandover(Uri,
     * int, PhoneAccountHandle) which the receiving side of the handover uses to
     * accept a handover.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.ACCEPT_HANDOVER"
     */
    public static final String ACCEPT_HANDOVER = "android.permission.ACCEPT_HANDOVER";

    /**
     * Allows an app to access location in the background. If you're requesting
     * this permission, you must also request either ACCESS_COARSE_LOCATION or
     * ACCESS_FINE_LOCATION. Requesting this permission by itself doesn't give
     * you location access.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_BACKGROUND_LOCATION"
     */
    public static final String ACCESS_CHECKIN_PROPERTIES = "android.permission.ACCESS_CHECKIN_PROPERTIES";

    /**
     * Allows an app to access approximate location. Alternatively, you might
     * want ACCESS_FINE_LOCATION.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_COARSE_LOCATION"
     */
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";

    /**
     * Allows an app to access precise location. Alternatively, you might want
     * ACCESS_COARSE_LOCATION.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_FINE_LOCATION"
     */
    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";

    /**
     * Allows an application to access extra location provider commands.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"
     */
    public static final String ACCESS_LOCATION_EXTRA_COMMANDS = "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS";

    /**
     * Allows an application to access any geographic locations persisted in the
     * user's shared collection.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_MEDIA_LOCATION"
     */
    public static final String ACCESS_MEDIA_LOCATION = "android.permission.ACCESS_MEDIA_LOCATION";

    /**
     * Allows applications to access information about networks.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_NETWORK_STATE"
     */
    public static final String ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";

    /**
     * Marker permission for applications that wish to access notification
     * policy. This permission is not supported on managed profiles.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_NOTIFICATION_POLICY"
     */
    public static final String ACCESS_NOTIFICATION_POLICY = "android.permission.ACCESS_NOTIFICATION_POLICY";

    /**
     * Allows applications to access information about Wi-Fi networks.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.ACCESS_WIFI_STATE"
     */
    public static final String ACCESS_WIFI_STATE = "android.permission.ACCESS_WIFI_STATE";

    /**
     * Allows an application to recognize physical activity.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.ACTIVITY_RECOGNITION"
     */
    public static final String ACTIVITY_RECOGNITION = "android.permission.ACTIVITY_RECOGNITION";

    /**
     * Allows an application to add voicemails into the system.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "com.android.voicemail.permission.ADD_VOICEMAIL"
     */
    public static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";

    /**
     * Allows the app to answer an incoming phone call.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.ANSWER_PHONE_CALLS"
     */
    public static final String ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS";

    /**
     * Allows an application to collect battery statistics<br>
     * <br>
     * Protection level: signature|privileged|development<br>
     * <br>
     * Constant Value: "android.permission.BATTERY_STATS"
     */
    public static final String BATTERY_STATS = "android.permission.BATTERY_STATS";

    /**
     * Must be required by an AccessibilityService, to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_ACCESSIBILITY_SERVICE"
     */
    public static final String BIND_ACCESSIBILITY_SERVICE = "android.permission.BIND_ACCESSIBILITY_SERVICE";

    /**
     * Allows an application to tell the AppWidget service which application can
     * access AppWidget's data. The normal user flow is that a user picks an
     * AppWidget to go into a particular host, thereby giving that host
     * application access to the private data from the AppWidget app. An
     * application that has this permission should honor that contract.<br>
     * <br>
     * Not for use by third-party applications.<br>
     * <br>
     * Constant Value: "android.permission.BIND_APPWIDGET"
     */
    public static final String BIND_APPWIDGET = "android.permission.BIND_APPWIDGET";

    /**
     * Must be required by a AutofillService, to ensure that only the system can
     * bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_AUTOFILL_SERVICE"
     */
    public static final String BIND_AUTOFILL_SERVICE = "android.permission.BIND_AUTOFILL_SERVICE";

    /**
     * Must be required by a CallRedirectionService, to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_CALL_REDIRECTION_SERVICE"
     */
    public static final String BIND_CALL_REDIRECTION_SERVICE = "android.permission.BIND_CALL_REDIRECTION_SERVICE";

    /**
     * A subclass of CarrierMessagingClientService must be protected with this
     * permission.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value:
     * "android.permission.BIND_CARRIER_MESSAGING_CLIENT_SERVICE"
     */
    public static final String BIND_CARRIER_MESSAGING_CLIENT_SERVICE = "android.permission.BIND_CARRIER_MESSAGING_CLIENT_SERVICE";

    /**
     * This constant was deprecated in API level 23. Use BIND_CARRIER_SERVICES
     * instead<br>
     * <br>
     * Constant Value: "android.permission.BIND_CARRIER_MESSAGING_SERVICE"
     */
    @Deprecated
    public static final String BIND_CARRIER_MESSAGING_SERVICE = "android.permission.BIND_CARRIER_MESSAGING_SERVICE";

    /**
     * The system process that is allowed to bind to services in carrier apps
     * will have this permission. Carrier apps should use this permission to
     * protect their services that only the system is allowed to bind to.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_CARRIER_SERVICES"
     */
    public static final String BIND_CARRIER_SERVICES = "android.permission.BIND_CARRIER_SERVICES";

    /**
     * This constant was deprecated in API level 30. For publishing direct share
     * targets, please follow the instructions in
     * https://developer.android.com/training/sharing/receive.html#providing-direct-share-targets
     * instead.<br>
     * <br>
     * Must be required by a ChooserTargetService, to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_CHOOSER_TARGET_SERVICE"
     */
    @Deprecated
    public static final String BIND_CHOOSER_TARGET_SERVICE = "android.permission.BIND_CHOOSER_TARGET_SERVICE";

    /**
     * Must be required by a ConditionProviderService, to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_CONDITION_PROVIDER_SERVICE"
     */
    public static final String BIND_CONDITION_PROVIDER_SERVICE = "android.permission.BIND_CONDITION_PROVIDER_SERVICE";

    /**
     * Must be required by device administration receiver, to ensure that only
     * the system can interact with it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_DEVICE_ADMIN"
     */
    public static final String BIND_DEVICE_ADMIN = "android.permission.BIND_DEVICE_ADMIN";

    /**
     * Must be required by an DreamService, to ensure that only the system can
     * bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_DREAM_SERVICE"
     */
    public static final String BIND_DREAM_SERVICE = "android.permission.BIND_DREAM_SERVICE";

    /**
     * Must be required by a InCallService, to ensure that only the system can
     * bind to it.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_INCALL_SERVICE"
     */
    public static final String BIND_INCALL_SERVICE = "android.permission.BIND_INCALL_SERVICE";

    /**
     * Must be required by an InputMethodService, to ensure that only the system
     * can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_INPUT_METHOD"
     */
    public static final String BIND_INPUT_METHOD = "android.permission.BIND_INPUT_METHOD";

    /**
     * Must be required by an MidiDeviceService, to ensure that only the system
     * can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_MIDI_DEVICE_SERVICE"
     */
    public static final String BIND_MIDI_DEVICE_SERVICE = "android.permission.BIND_MIDI_DEVICE_SERVICE";

    /**
     * Must be required by a HostApduService or OffHostApduService to ensure
     * that only the system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_NFC_SERVICE"
     */
    public static final String BIND_NFC_SERVICE = "android.permission.BIND_NFC_SERVICE";

    /**
     * Must be required by an NotificationListenerService, to ensure that only
     * the system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE"
     */
    public static final String BIND_NOTIFICATION_LISTENER_SERVICE = "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE";

    /**
     * Must be required by a PrintService, to ensure that only the system can
     * bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_PRINT_SERVICE"
     */
    public static final String BIND_PRINT_SERVICE = "android.permission.BIND_PRINT_SERVICE";

    /**
     * Must be required by a QuickAccessWalletService to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_QUICK_ACCESS_WALLET_SERVICE"
     */
    public static final String BIND_QUICK_SETTINGS_TILE = "android.permission.BIND_QUICK_SETTINGS_TILE";

    /**
     * Must be required by a QuickAccessWalletService to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_QUICK_ACCESS_WALLET_SERVICE"
     */
    public static final String BIND_REMOTEVIEWS = "android.permission.BIND_REMOTEVIEWS";

    /**
     * Must be required by a CallScreeningService, to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_SCREENING_SERVICE"
     */
    public static final String BIND_SCREENING_SERVICE = "android.permission.BIND_SCREENING_SERVICE";

    /**
     * Must be required by a ConnectionService, to ensure that only the system
     * can bind to it.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_TELECOM_CONNECTION_SERVICE"
     */
    public static final String BIND_TELECOM_CONNECTION_SERVICE = "android.permission.BIND_TELECOM_CONNECTION_SERVICE";

    /**
     * Must be required by a TextService (e.g. SpellCheckerService) to ensure
     * that only the system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_TEXT_SERVICE"
     */
    public static final String BIND_TEXT_SERVICE = "android.permission.BIND_TEXT_SERVICE";

    /**
     * Must be required by a TvInputService to ensure that only the system can
     * bind to it.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_TV_INPUT"
     */
    public static final String BIND_TV_INPUT = "android.permission.BIND_TV_INPUT";

    /**
     * Must be required by a link VisualVoicemailService to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_VISUAL_VOICEMAIL_SERVICE"
     */
    public static final String BIND_VISUAL_VOICEMAIL_SERVICE = "android.permission.BIND_VISUAL_VOICEMAIL_SERVICE";

    /**
     * Must be required by a VoiceInteractionService, to ensure that only the
     * system can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_VOICE_INTERACTION"
     */
    public static final String BIND_VOICE_INTERACTION = "android.permission.BIND_VOICE_INTERACTION";

    /**
     * Must be required by a VpnService, to ensure that only the system can bind
     * to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_VPN_SERVICE"
     */
    public static final String BIND_VPN_SERVICE = "android.permission.BIND_VPN_SERVICE";

    /**
     * Must be required by an VrListenerService, to ensure that only the system
     * can bind to it.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.BIND_VR_LISTENER_SERVICE"
     */
    public static final String BIND_VR_LISTENER_SERVICE = "android.permission.BIND_VR_LISTENER_SERVICE";

    /**
     * Must be required by a WallpaperService, to ensure that only the system
     * can bind to it.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.BIND_WALLPAPER"
     */
    public static final String BIND_WALLPAPER = "android.permission.BIND_WALLPAPER";

    /**
     * Allows applications to connect to paired bluetooth devices.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.BLUETOOTH"
     */
    public static final String BLUETOOTH = "android.permission.BLUETOOTH";

    /**
     * Allows applications to discover and pair bluetooth devices.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.BLUETOOTH_ADMIN"
     */
    public static final String BLUETOOTH_ADMIN = "android.permission.BLUETOOTH_ADMIN";

    /**
     * Allows applications to pair bluetooth devices without user interaction,
     * and to allow or disallow phonebook access or message access.<br>
     * <br>
     * Not for use by third-party applications.<br>
     * <br>
     * Constant Value: "android.permission.BLUETOOTH_PRIVILEGED"
     */
    public static final String BLUETOOTH_PRIVILEGED = "android.permission.BLUETOOTH_PRIVILEGED";

    /**
     * Allows an application to access data from sensors that the user uses to
     * measure what is happening inside their body, such as heart rate.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.BODY_SENSORS"
     */
    public static final String BODY_SENSORS = "android.permission.BODY_SENSORS";

    /**
     * Allows an application to broadcast sticky intents. These are broadcasts
     * whose data is held by the system after being finished, so that clients
     * can quickly retrieve that data without having to wait for the next
     * broadcast.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.BROADCAST_STICKY"
     */
    public static final String BROADCAST_STICKY = "android.permission.BROADCAST_STICKY";

    /**
     * Allows an app which implements the InCallService API to be eligible to be
     * enabled as a calling companion app. This means that the Telecom framework
     * will bind to the app's InCallService implementation when there are calls
     * active. The app can use the InCallService API to view information about
     * calls on the system and control these calls.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.CALL_COMPANION_APP"
     */
    public static final String CALL_COMPANION_APP = "android.permission.CALL_COMPANION_APP";

    /**
     * Allows an application to initiate a phone call without going through the
     * Dialer user interface for the user to confirm the call.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.CALL_PHONE"
     */
    public static final String CALL_PHONE = "android.permission.CALL_PHONE";

    /**
     * Allows an application to call any phone number, including emergency
     * numbers, without going through the Dialer user interface for the user to
     * confirm the call being placed.<br>
     * <br>
     * Not for use by third-party applications.<br>
     * <br>
     * Constant Value: "android.permission.CALL_PRIVILEGED"
     */
    public static final String CALL_PRIVILEGED = "android.permission.CALL_PRIVILEGED";

    /**
     * Required to be able to access the camera device.<br>
     * <br>
     * This will automatically enforce the uses-feature manifest element for all
     * camera features. If you do not require all camera features or can
     * properly operate if a camera is not available, then you must modify your
     * manifest as appropriate in order to install on devices that don't support
     * all camera features.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.CAMERA"
     */
    public static final String CAMERA = "android.permission.CAMERA";

    /**
     * Allows an application to modify the current configuration, such as
     * locale.<br>
     * <br>
     * Protection level: signature|privileged|development<br>
     * <br>
     * Constant Value: "android.permission.CHANGE_CONFIGURATION"
     */
    public static final String CHANGE_CONFIGURATION = "android.permission.CHANGE_CONFIGURATION";

    /**
     * Allows applications to change network connectivity state.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.CHANGE_NETWORK_STATE"
     */
    public static final String CHANGE_NETWORK_STATE = "android.permission.CHANGE_NETWORK_STATE";

    /**
     * Allows applications to enter Wi-Fi Multicast mode.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.CHANGE_WIFI_MULTICAST_STATE"
     */
    public static final String CHANGE_WIFI_MULTICAST_STATE = "android.permission.CHANGE_WIFI_MULTICAST_STATE";

    /**
     * Allows applications to change Wi-Fi connectivity state.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.CHANGE_WIFI_STATE"
     */
    public static final String CHANGE_WIFI_STATE = "android.permission.CHANGE_WIFI_STATE";

    /**
     * Allows an application to clear the caches of all installed applications
     * on the device.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.CLEAR_APP_CACHE"
     */
    public static final String CLEAR_APP_CACHE = "android.permission.CLEAR_APP_CACHE";

    /**
     * Old permission for deleting an app's cache files, no longer used, but
     * signals for us to quietly ignore calls instead of throwing an
     * exception.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.DELETE_CACHE_FILES"
     */
    public static final String DELETE_CACHE_FILES = "android.permission.DELETE_CACHE_FILES";

    /**
     * Allows applications to disable the keyguard if it is not secure.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.DISABLE_KEYGUARD"
     */
    public static final String DISABLE_KEYGUARD = "android.permission.DISABLE_KEYGUARD";

    /**
     * Allows an application to expand or collapse the status bar.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.EXPAND_STATUS_BAR"
     */
    public static final String EXPAND_STATUS_BAR = "android.permission.EXPAND_STATUS_BAR";

    /**
     * Allows a regular application to use Service.startForeground.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.FOREGROUND_SERVICE"
     */
    public static final String FOREGROUND_SERVICE = "android.permission.FOREGROUND_SERVICE";

    /**
     * public static final String GET_ACCOUNTS<br>
     * <br>
     * Allows access to the list of accounts in the Accounts Service.<br>
     * <br>
     * Note: Beginning with Android 6.0 (API level 23), if an app shares the
     * signature of the authenticator that manages an account, it does not need
     * "GET_ACCOUNTS" permission to read information about that account. On
     * Android 5.1 and lower, all apps need "GET_ACCOUNTS" permission to read
     * information about any account.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.GET_ACCOUNTS"
     */
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";

    /**
     * Allows access to the list of accounts in the Accounts Service.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.GET_ACCOUNTS_PRIVILEGED"
     */
    public static final String GET_ACCOUNTS_PRIVILEGED = "android.permission.GET_ACCOUNTS_PRIVILEGED";

    /**
     * Allows an application to find out the space used by any package.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.GET_PACKAGE_SIZE"
     */
    public static final String GET_PACKAGE_SIZE = "android.permission.GET_PACKAGE_SIZE";
    /**
     * This constant was deprecated in API level 21. No longer enforced.<br>
     * <br>
     * Constant Value: "android.permission.GET_TASKS"
     */
    @Deprecated
    public static final String GET_TASKS = "android.permission.GET_TASKS";

    /**
     * This permission can be used on content providers to allow the global
     * search system to access their data. Typically it used when the provider
     * has some permissions protecting it (which global search would not be
     * expected to hold), and added as a read-only permission to the path in the
     * provider where global search queries are performed. This permission can
     * not be held by regular applications; it is used by applications to
     * protect themselves from everyone else besides global search.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "android.permission.GLOBAL_SEARCH"
     */
    public static final String GLOBAL_SEARCH = "android.permission.GLOBAL_SEARCH";

    /**
     * Allows an application to install a location provider into the Location
     * Manager.<br>
     * <br>
     * Not for use by third-party applications.<br>
     * <br>
     * Constant Value: "android.permission.INSTALL_LOCATION_PROVIDER"
     */
    public static final String INSTALL_LOCATION_PROVIDER = "android.permission.INSTALL_LOCATION_PROVIDER";

    /**
     * Allows an application to install a shortcut in Launcher.<br>
     * <br>
     * In Android O (API level 26) and higher, the INSTALL_SHORTCUT broadcast no
     * longer has any effect on your app because it's a private, implicit
     * broadcast. Instead, you should create an app shortcut by using the
     * requestPinShortcut() method from the ShortcutManager class.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "com.android.launcher.permission.INSTALL_SHORTCUT"
     */
    public static final String INSTALL_SHORTCUT = "com.android.launcher.permission.INSTALL_SHORTCUT";

    /**
     * Allows an instant app to create foreground services.<br>
     * <br>
     * Protection level: signature|development|instant|appop<br>
     * <br>
     * Constant Value: "android.permission.INSTANT_APP_FOREGROUND_SERVICE"
     */
    public static final String INSTANT_APP_FOREGROUND_SERVICE = "android.permission.INSTANT_APP_FOREGROUND_SERVICE";

    /**
     * Allows interaction across profiles in the same profile group.<br>
     * <br>
     * Constant Value: "android.permission.INTERACT_ACROSS_PROFILES"
     */
    public static final String INTERACT_ACROSS_PROFILES = "";

    /**
     * Allows applications to open network sockets.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.INTERNET"
     */
    public static final String INTERNET = "android.permission.INTERNET";

    /**
     * Allows an application to call
     * ActivityManager.killBackgroundProcesses(String).<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.KILL_BACKGROUND_PROCESSES"
     */
    public static final String KILL_BACKGROUND_PROCESSES = "android.permission.KILL_BACKGROUND_PROCESSES";

    /**
     * Allows a data loader to read a package's access logs. The access logs
     * contain the set of pages referenced over time.<br>
     * <br>
     * Declaring the permission implies intention to use the API and the user of
     * the device can grant permission through the Settings application.<br>
     * <br>
     * Protection level: signature|privileged|appop<br>
     * <br>
     * A data loader has to be the one which provides data to install an
     * app.<br>
     * <br>
     * A data loader has to have both permission:LOADER_USAGE_STATS AND
     * appop:LOADER_USAGE_STATS allowed to be able to access the read logs.<br>
     * <br>
     * Constant Value: "android.permission.LOADER_USAGE_STATS"
     */
    public static final String LOADER_USAGE_STATS = "android.permission.LOADER_USAGE_STATS";

    /**
     * Allows an application a broad access to external storage in scoped
     * storage. Intended to be used by few apps that need to manage files on
     * behalf of the users.<br>
     * <br>
     * Protection level: signature|appop|preinstalled<br>
     * <br>
     * Constant Value: "android.permission.MANAGE_EXTERNAL_STORAGE"
     */
    public static final String MANAGE_EXTERNAL_STORAGE = "android.permission.MANAGE_EXTERNAL_STORAGE";

    /**
     * Allows a calling application which manages it own calls through the
     * self-managed ConnectionService APIs. See
     * PhoneAccount.CAPABILITY_SELF_MANAGED for more information on the
     * self-managed ConnectionService APIs.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.MANAGE_OWN_CALLS"
     */
    public static final String MANAGE_OWN_CALLS = "android.permission.MANAGE_OWN_CALLS";

    /**
     * Allows an application to modify global audio settings.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.MODIFY_AUDIO_SETTINGS"
     */
    public static final String MODIFY_AUDIO_SETTINGS = "android.permission.MODIFY_AUDIO_SETTINGS";

    /**
     * Allows applications to perform I/O operations over NFC.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.NFC"
     */
    public static final String NFC = "android.permission.NFC";

    /**
     * Allows applications to receive NFC preferred payment service
     * information.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.NFC_PREFERRED_PAYMENT_INFO"
     */
    public static final String NFC_PREFERRED_PAYMENT_INFO = "android.permission.NFC_PREFERRED_PAYMENT_INFO";

    /**
     * Allows applications to receive NFC transaction events.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.NFC_TRANSACTION_EVENT"
     */
    public static final String NFC_TRANSACTION_EVENT = "android.permission.NFC_TRANSACTION_EVENT";

    /**
     * Allows an application to collect component usage statistics<br>
     * <br>
     * Declaring the permission implies intention to use the API and the user of
     * the device can grant permission through the Settings application.<br>
     * <br>
     * Protection level: signature|privileged|development|appop|retailDemo<br>
     * <br>
     * Constant Value: "android.permission.PACKAGE_USAGE_STATS"
     */
    public static final String PACKAGE_USAGE_STATS = "android.permission.PACKAGE_USAGE_STATS";

    /**
     * This constant was deprecated in API level 15. This functionality will be
     * removed in the future; please do not use. Allow an application to make
     * its activities persistent.<br>
     * <br>
     * Constant Value: "android.permission.PERSISTENT_ACTIVITY"
     */
    @Deprecated
    public static final String PERSISTENT_ACTIVITY = "android.permission.PERSISTENT_ACTIVITY";

    /**
     * This constant was deprecated in API level 29. Applications should use
     * CallRedirectionService instead of the Intent.ACTION_NEW_OUTGOING_CALL
     * broadcast.<br>
     * <br>
     * Allows an application to see the number being dialed during an outgoing
     * call with the option to redirect the call to a different number or abort
     * the call altogether.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.PROCESS_OUTGOING_CALLS"
     */
    public static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";

    /**
     * Allows query of any normal app on the device, regardless of manifest
     * declarations.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.QUERY_ALL_PACKAGES"
     */
    public static final String QUERY_ALL_PACKAGES = "android.permission.QUERY_ALL_PACKAGES";

    /**
     * Allows an application to read the user's calendar data.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.READ_CALENDAR"
     */
    public static final String READ_CALENDAR = "android.permission.READ_CALENDAR";

    /**
     * Allows an application to read the user's call log.<br>
     * <br>
     * Note: If your app uses the READ_CONTACTS permission and both your
     * minSdkVersion and targetSdkVersion values are set to 15 or lower, the
     * system implicitly grants your app this permission. If you don't need this
     * permission, be sure your targetSdkVersion is 16 or higher.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.READ_CALL_LOG"
     */
    public static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";

    /**
     * Allows an application to read the user's contacts data.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.READ_CONTACTS"
     */
    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS";

    /**
     * Allows an application to read from external storage.<br>
     * <br>
     * Any app that declares the WRITE_EXTERNAL_STORAGE permission is implicitly
     * granted this permission.<br>
     * <br>
     * This permission is enforced starting in API level 19. Before API level
     * 19, this permission is not enforced and all apps still have access to
     * read from external storage. You can test your app with the permission
     * enforced by enabling Protect USB storage under Developer options in the
     * Settings app on a device running Android 4.1 or higher.<br>
     * <br>
     * Also starting in API level 19, this permission is not required to
     * read/write files in your application-specific directories returned by
     * Context.getExternalFilesDir(String) and
     * Context.getExternalCacheDir().<br>
     * <br>
     * Note: If both your minSdkVersion and targetSdkVersion values are set to 3
     * or lower, the system implicitly grants your app this permission. If you
     * don't need this permission, be sure your targetSdkVersion is 4 or
     * higher.<br>
     * <br>
     * This is a soft restricted permission which cannot be held by an app it
     * its full form until the installer on record whitelists the permission.
     * Specifically, if the permission is whitelisted the holder app can access
     * external storage and the visual and aural media collections while if the
     * permission is not whitelisted the holder app can only access to the
     * visual and aural medial collections. Also the permission is immutably
     * restricted meaning that the whitelist state can be specified only at
     * install time and cannot change until the app is installed. For more
     * details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.READ_EXTERNAL_STORAGE"
     */
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    /**
     * Allows read only access to phone state, including the current cellular
     * network information, the status of any ongoing calls, and a list of any
     * PhoneAccounts registered on the device.<br>
     * <br>
     * Note: If both your minSdkVersion and targetSdkVersion values are set to 3
     * or lower, the system implicitly grants your app this permission. If you
     * don't need this permission, be sure your targetSdkVersion is 4 or
     * higher.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.READ_PHONE_STATE"
     */
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";

    /**
     * Allows read only access to precise phone state. Allows reading of
     * detailed information about phone state for special-use applications such
     * as dialers, carrier applications, or ims applications.<br>
     * <br>
     * Constant Value: "android.permission.READ_PRECISE_PHONE_STATE"
     */
    public static final String READ_PRECISE_PHONE_STATE = "android.permission.READ_PRECISE_PHONE_STATE";

    /**
     * Allows an application to read SMS messages.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.READ_SMS"
     */
    public static final String READ_SMS = "android.permission.READ_SMS";

    /**
     * Allows applications to read the sync settings.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.READ_SYNC_SETTINGS"
     */
    public static final String READ_SYNC_SETTINGS = "android.permission.READ_SYNC_SETTINGS";

    /**
     * Allows applications to read the sync stats.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.READ_SYNC_STATS"
     */
    public static final String READ_SYNC_STATS = "android.permission.READ_SYNC_STATS";

    /**
     * Allows an application to read voicemails in the system.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "com.android.voicemail.permission.READ_VOICEMAIL"
     */
    public static final String READ_VOICEMAIL = "com.android.voicemail.permission.READ_VOICEMAIL";

    /**
     * Required to be able to reboot the device.<br>
     * <br>
     * Not for use by third-party applications.<br>
     * <br>
     * Constant Value: "android.permission.REBOOT"
     */
    public static final String REBOOT = "android.permission.REBOOT";

    /**
     * Allows an application to receive the Intent.ACTION_BOOT_COMPLETED that is
     * broadcast after the system finishes booting. If you don't request this
     * permission, you will not receive the broadcast at that time. Though
     * holding this permission does not have any security implications, it can
     * have a negative impact on the user experience by increasing the amount of
     * time it takes the system to start and allowing applications to have
     * themselves running without the user being aware of them. As such, you
     * must explicitly declare your use of this facility to make that visible to
     * the user.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.RECEIVE_BOOT_COMPLETED"
     */
    public static final String RECEIVE_BOOT_COMPLETED = "android.permission.RECEIVE_BOOT_COMPLETED";

    /**
     * Allows an application to monitor incoming MMS messages.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.RECEIVE_MMS"
     */
    public static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";

    /**
     * Allows an application to receive SMS messages.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.RECEIVE_SMS"
     */
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";

    /**
     * Allows an application to receive WAP push messages.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.RECEIVE_WAP_PUSH"
     */
    public static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";

    /**
     * Allows an application to record audio.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.RECORD_AUDIO"
     */
    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";

    /**
     * Allows an application to change the Z-order of tasks.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.REORDER_TASKS"
     */
    public static final String REORDER_TASKS = "android.permission.REORDER_TASKS";

    /**
     * Allows a companion app to run in the background.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND"
     */
    public static final String REQUEST_COMPANION_RUN_IN_BACKGROUND = "android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND";

    /**
     * Allows a companion app to use data in the background.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value:
     * "android.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND"
     */
    public static final String REQUEST_COMPANION_USE_DATA_IN_BACKGROUND = "android.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND";

    /**
     * Allows an application to request deleting packages. Apps targeting APIs
     * Build.VERSION_CODES.P or greater must hold this permission in order to
     * use Intent.ACTION_UNINSTALL_PACKAGE or
     * PackageInstaller.uninstall(VersionedPackage, IntentSender).<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.REQUEST_DELETE_PACKAGES"
     */
    public static final String REQUEST_DELETE_PACKAGES = "android.permission.REQUEST_DELETE_PACKAGES";

    /**
     * Permission an application must hold in order to use
     * Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"
     */
    public static final String REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";

    /**
     * Allows an application to request installing packages. Apps targeting APIs
     * greater than 25 must hold this permission in order to use
     * Intent.ACTION_INSTALL_PACKAGE.<br>
     * <br>
     * Protection level: signature<br>
     * <br>
     * Constant Value: "android.permission.REQUEST_INSTALL_PACKAGES"
     */
    public static final String REQUEST_INSTALL_PACKAGES = "android.permission.REQUEST_INSTALL_PACKAGES";

    /**
     * Allows an application to request the screen lock complexity and prompt
     * users to update the screen lock to a certain complexity level.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.REQUEST_PASSWORD_COMPLEXITY"
     */
    public static final String REQUEST_PASSWORD_COMPLEXITY = "android.permission.REQUEST_PASSWORD_COMPLEXITY";

    /**
     * This constant was deprecated in API level 15. The
     * ActivityManager.restartPackage(String) API is no longer supported.<br>
     * <br>
     * Constant Value: "android.permission.RESTART_PACKAGES"
     */
    @Deprecated
    public static final String RESTART_PACKAGES = "android.permission.RESTART_PACKAGES";

    /**
     * Allows an application to send SMS messages.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.SEND_SMS"
     */
    public static final String SEND_SMS = "android.permission.SEND_SMS";

    /**
     * Allows an application to broadcast an Intent to set an alarm for the
     * user.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "com.android.alarm.permission.SET_ALARM"
     */
    public static final String SET_ALARM = "com.android.alarm.permission.SET_ALARM";

    /**
     * Allows an application to control whether activities are immediately
     * finished when put in the background.<br>
     * <br>
     * Not for use by third-party applications.<br>
     * <br>
     * Constant Value: "android.permission.SET_ALWAYS_FINISH"
     */
    public static final String SET_ALWAYS_FINISH = "android.permission.SET_ALWAYS_FINISH";

    /**
     * Modify the global animation scaling factor.<br>
     * <br>
     * Not for use by third-party applications.<br>
     * <br>
     * Constant Value: "android.permission.SET_ANIMATION_SCALE"
     */
    public static final String SET_ANIMATION_SCALE = "android.permission.SET_ANIMATION_SCALE";

    /**
     * This constant was deprecated in API level 15. No longer useful, see
     * PackageManager.addPackageToPreferred(String) for details.<br>
     * <br>
     * Constant Value: "android.permission.SET_PREFERRED_APPLICATIONS"
     */
    @Deprecated
    public static final String SET_PREFERRED_APPLICATIONS = "android.permission.SET_PREFERRED_APPLICATIONS";

    /**
     * Allows applications to set the wallpaper.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.SET_WALLPAPER"
     */
    public static final String SET_WALLPAPER = "android.permission.SET_WALLPAPER";

    /**
     * Allows applications to set the wallpaper hints.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.SET_WALLPAPER_HINTS"
     */
    public static final String SET_WALLPAPER_HINTS = "android.permission.SET_WALLPAPER_HINTS";

    /**
     * Allows financial apps to read filtered sms messages.<br>
     * <br>
     * Protection level: signature|appop<br>
     * <br>
     * Constant Value: "android.permission.SMS_FINANCIAL_TRANSACTIONS"
     */
    public static final String SMS_FINANCIAL_TRANSACTIONS = "android.permission.SMS_FINANCIAL_TRANSACTIONS";

    /**
     * Allows the holder to start the permission usage screen for an app.<br>
     * <br>
     * Protection level: signature|installer<br>
     * <br>
     * Constant Value: "android.permission.START_VIEW_PERMISSION_USAGE"
     */
    public static final String START_VIEW_PERMISSION_USAGE = "android.permission.START_VIEW_PERMISSION_USAGE";

    /**
     * Allows an app to create windows using the type
     * WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, shown on top of all
     * other apps. Very few apps should use this permission; these windows are
     * intended for system-level interaction with the user.<br>
     * <br>
     * Note: If the app targets API level 23 or higher, the app user must
     * explicitly grant this permission to the app through a permission
     * management screen. The app requests the user's approval by sending an
     * intent with action Settings.ACTION_MANAGE_OVERLAY_PERMISSION. The app can
     * check whether it has this authorization by calling
     * Settings.canDrawOverlays().<br>
     * <br>
     * Protection level: signature|preinstalled|appop|pre23|development<br>
     * <br>
     * Constant Value: "android.permission.SYSTEM_ALERT_WINDOW"
     */
    public static final String SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW";

    /**
     * Allows using the device's IR transmitter, if available.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.TRANSMIT_IR"
     */
    public static final String TRANSMIT_IR = "android.permission.TRANSMIT_IR";

    /**
     * Allows an app to use device supported biometric modalities.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.USE_BIOMETRIC"
     */
    public static final String USE_BIOMETRIC = "android.permission.USE_BIOMETRIC";

    /**
     * This constant was deprecated in API level 28. Applications should request
     * USE_BIOMETRIC instead<br>
     * <br>
     * Allows an app to use fingerprint hardware.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.USE_FINGERPRINT"
     */
    @Deprecated
    public static final String USE_FINGERPRINT = "android.permission.USE_FINGERPRINT";

    /**
     * Required for apps targeting Build.VERSION_CODES.Q that want to use
     * notification full screen intents.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.USE_FULL_SCREEN_INTENT"
     */
    public static final String USE_FULL_SCREEN_INTENT = "android.permission.USE_FULL_SCREEN_INTENT";

    /**
     * Allows an application to use SIP service.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.USE_SIP"
     */
    public static final String USE_SIP = "android.permission.USE_SIP";

    /**
     * Allows access to the vibrator.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.VIBRATE"
     */
    public static final String VIBRATE = "android.permission.VIBRATE";

    /**
     * Allows using PowerManager WakeLocks to keep processor from sleeping or
     * screen from dimming.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.WAKE_LOCK"
     */
    public static final String WAKE_LOCK = "android.permission.WAKE_LOCK";

    /**
     * Allows an application to write the user's calendar data.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.WRITE_CALENDAR"
     */
    public static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";

    /**
     * Allows an application to write (but not read) the user's call log
     * data.<br>
     * <br>
     * Note: If your app uses the WRITE_CONTACTS permission and both your
     * minSdkVersion and targetSdkVersion values are set to 15 or lower, the
     * system implicitly grants your app this permission. If you don't need this
     * permission, be sure your targetSdkVersion is 16 or higher.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * This is a hard restricted permission which cannot be held by an app until
     * the installer on record whitelists the permission. For more details see
     * PackageInstaller.SessionParams.setWhitelistedRestrictedPermissions(Set).<br>
     * <br>
     * Constant Value: "android.permission.WRITE_CALL_LOG"
     */
    public static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";

    /**
     * Allows an application to write the user's contacts data.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.WRITE_CONTACTS"
     */
    public static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";

    /**
     * Allows an application to write to external storage.<br>
     * <br>
     * Note: If both your minSdkVersion and targetSdkVersion values are set to 3
     * or lower, the system implicitly grants your app this permission. If you
     * don't need this permission, be sure your targetSdkVersion is 4 or
     * higher.<br>
     * <br>
     * Starting in API level 19, this permission is not required to read/write
     * files in your application-specific directories returned by
     * Context.getExternalFilesDir(String) and
     * Context.getExternalCacheDir().<br>
     * <br>
     * If this permission is not whitelisted for an app that targets an API
     * level before Build.VERSION_CODES.Q this permission cannot be granted to
     * apps.<br>
     * <br>
     * Protection level: dangerous<br>
     * <br>
     * Constant Value: "android.permission.WRITE_EXTERNAL_STORAGE"
     */
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

    /**
     * Allows an application to read or write the system settings.<br>
     * <br>
     * Note: If the app targets API level 23 or higher, the app user must
     * explicitly grant this permission to the app through a permission
     * management screen. The app requests the user's approval by sending an
     * intent with action Settings.ACTION_MANAGE_WRITE_SETTINGS. The app can
     * check whether it has this authorization by calling
     * Settings.System.canWrite().<br>
     * <br>
     * Protection level: signature|preinstalled|appop|pre23<br>
     * <br>
     * Constant Value: "android.permission.WRITE_SETTINGS"
     */
    public static final String WRITE_SETTINGS = "android.permission.WRITE_SETTINGS";

    /**
     * Allows applications to write the sync settings.<br>
     * <br>
     * Protection level: normal<br>
     * <br>
     * Constant Value: "android.permission.WRITE_SYNC_SETTINGS"
     */
    public static final String WRITE_SYNC_SETTINGS = "android.permission.WRITE_SYNC_SETTINGS";

    /**
     * Allows an application to modify and remove existing voicemails in the
     * system.<br>
     * <br>
     * Protection level: signature|privileged<br>
     * <br>
     * Constant Value: "com.android.voicemail.permission.WRITE_VOICEMAIL"
     */
    public static final String WRITE_VOICEMAIL = "com.android.voicemail.permission.WRITE_VOICEMAIL";
}
