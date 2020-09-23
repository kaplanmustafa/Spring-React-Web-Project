import i18n from "i18next";
import { initReactI18next } from "react-i18next";

i18n.use(initReactI18next).init({
  resources: {
    en: {
      translations: {
        "Sign Up": "Sign Up",
        "Password mismatch": "Password mismatch",
        "Username": "Username",
        "Display Name": "Display Name",
        "Password": "Password",
        "Password Repeat": "Password Repeat",
        "Login": "Login",
        "Logout": "Logout",
        "Users": "Users",
        "Next": "next >",
        "Previous": "< previous",
        "Load Failure": "Load Failure",
        "User Not Found!": "User Not Found!"
      }
    },
    tr: {
      translations: {
        "Sign Up": "Kaydol",
        "Password mismatch": "Şifreler eşleşmiyor",
        "Username": "Kullanıcı Adı",
        "Display Name": "Tercih Edilen İsim",
        "Password": "Şifre",
        "Password Repeat": "Şifre Tekrar",
        "Login": "Giriş Yap",
        "Logout": "Çıkış",
        "Users": "Kullanıcılar",
        "Next": "sonraki >",
        "Previous": "< önceki",
        "Load Failure": "Liste alınamadı",
        "User Not Found!": "Kullanıcı Bulunamadı!"
      }
    }
  },
  fallbackLng: "en", // hata durumunda dil ne olsun
  ns: ["translations"],
  defaultNS: "translations",
  keySeparator: false,
  interpolation: {
    escapeValue: false,
    formatSeparator: ","
  },
  react: {
    wait: true
  }
});

export default i18n;