function doGet(e) {
  var json;
  try {
    var p = e.parameter;
    var currentLang = "ja"
    var text = p.text
    p.relays.split("/").forEach(lang => {
      text = LanguageApp.translate(text, currentLang, lang)
      currentLang = lang
    })
    json = { code: "successful", retranslated: text };
  } catch (e) {
    json = { code: "error", message: e.toString() };
  }
  return ContentService.createTextOutput(JSON.stringify(json)).setMimeType(ContentService.MimeType.JSON);
}
