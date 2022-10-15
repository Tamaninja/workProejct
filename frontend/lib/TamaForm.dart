class TamaForm {
  String? endpoint;
  List<Fields>? fields;

  TamaForm({this.endpoint, this.fields});

  TamaForm.fromJson(Map<String, dynamic> json) {
    endpoint = json['endpoint'];
    if (json['fields'] != null) {
      fields = <Fields>[];
      json['fields'].forEach((v) {
        fields!.add(new Fields.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['endpoint'] = this.endpoint;
    if (this.fields != null) {
      data['fields'] = this.fields!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Fields {
  Value? value;
  String? jsonKey;
  List<dynamic>? possibleValues;

  Fields({this.value, this.jsonKey, this.possibleValues});

  Fields.fromJson(Map<String, dynamic> json) {
    value = json['value'] != null ? new Value.fromJson(json['value']) : null;
    jsonKey = json['jsonKey'];
    if (json['possibleValues'] != null) {
      possibleValues = <dynamic>[];
      json['possibleValues'].forEach((v) {
        possibleValues!.add(v);
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.value != null) {
      data['value'] = this.value!.toJson();
    }
    data['jsonKey'] = this.jsonKey;
    if (this.possibleValues != null) {
      data['possibleValues'] =
          this.possibleValues!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Value {
  String? identifier;
  int? weightModifier;
  double? weight;

  Value({this.identifier, this.weightModifier, this.weight});

  Value.fromJson(Map<String, dynamic> json) {
    identifier = json['identifier'];
    weightModifier = json['weightModifier'];
    weight = json['weight'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['identifier'] = this.identifier;
    data['weightModifier'] = this.weightModifier;
    data['weight'] = this.weight;
    return data;
  }
}
