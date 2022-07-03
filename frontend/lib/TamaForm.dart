
class TamaList {
  String key = "key";
  dynamic value = "value";
  List<TamaCard> options = <TamaCard>[];

  TamaList(this.key, this.value, this.options);

  TamaList.fromJson(Map<String, dynamic> json) {
    key = json['key'];
    value = json['value'];
    options = <TamaCard>[];
    if (json['options'] != null) {
      json['options'].forEach((v) {
        options.add(TamaCard.fromJson(v, this));
      });
    } else {
      options.add(TamaCard(key,value, this));
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['key'] = key;
    data['value'] = value;
    data['options'] = options.map((v) => v.toJson()).toList();
    return data;
  }
}

class TamaForm {
  String endpoint;
  List<TamaList> list = <TamaList>[];
  TamaForm(this.endpoint);
}

class TamaCard {
  dynamic title;
  dynamic subtitle;
  TamaList parent;
  TamaCard(this.title, this.subtitle, this.parent);

  TamaCard.fromJson(Map<String, dynamic> json, this.parent) {
    title = json[json.keys.first];
    subtitle = json[json.keys.last];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['title'] = title;
    data['subtitle'] = subtitle;
    return data;
  }
}