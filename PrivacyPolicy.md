# Privacy Policy
**Kinetic Wand** 

Application uses 

* Speech recognizer API (aka voice recognition), android built-in
https://developer.android.com/reference/android/speech/SpeechRecognizer .
   Microphone and speech recognition API are used to listen for magic words. The "listening" is controlled by a touch button. Only when the button is being pressed, the speech recognizer service and thus the microphone is turned on. The result of speech recognition is compared to the a list of spells inside tha application to decide if the magid was successful or not. After the comparison the data is not used in any form. It is not kept, stored, processed anymore. 
   You may find the recognized word in the DEBUG logs.
     * Privileges needed: 
       * Manifest.permission.RECORD_AUDIO.

* Nearby Connections API, android built in
https://developer.android.com/distribute/best-practices/engage/nearby-interactions
https://developers.google.com/nearby/connections/overview . It is using public Bloetooth and WiFi channels to find devices running Kinetic Wand nearby your location. It is independent from the Mobile network and internet access.
   Wifi and Bloetooth are enabled when searching for opponents nearby. Once located and connected, you can challenge him/her for a duel.
It is controlled by the multiplayer switch. 
   * Privileges needed: 
     * android.permission.BLUETOOTH
     * android.permission.BLUETOOTH_ADMIN
     * android.permission.ACCESS_WIFI_STATE 
     * android.permission.CHANGE_WIFI_STATE 
     * android.permission.ACCESS_COARSE_LOCATION 
     * android.permission.ACCESS_FINE_LOCATION.

* Accelerometers (hardware)
This is the way the application knows if you have deone the movement correctly. There are kinetic patterns (X, Y, Z axis accelerometer data) preconfigured in the game. You can alter these and add your own movement data too. (see customization)
