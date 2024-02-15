rm trustStore.jks
keytool -genkey -alias mytruststore -keyalg RSA -keystore trustStore.jks -storepass meliPsw123
keytool -import -alias mercadolibre.com -file mercadolibre.com.cer -keystore trustStore.jks -storepass meliPsw123
keytool -list -keystore truststore.jks -storepass meliPsw123