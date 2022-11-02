// To parse this JSON data, do
//
//     final welcome = welcomeFromJson(jsonString);

import 'dart:convert';

Welcome welcomeFromJson(String str) => Welcome.fromJson(json.decode(str));

String welcomeToJson(Welcome data) => json.encode(data.toJson());

class Welcome {
  Welcome({
    this.endpoint,
    this.fields,
  });

  String endpoint;
  List<Field> fields;

  factory Welcome.fromJson(Map<String, dynamic> json) => Welcome(
    endpoint: json["endpoint"],
    fields: List<Field>.from(json["fields"].map((x) => Field.fromJson(x))),
  );

  Map<String, dynamic> toJson() => {
    "endpoint": endpoint,
    "fields": List<dynamic>.from(fields.map((x) => x.toJson())),
  };
}

class Field {
  Field({
    this.value,
    this.jsonKey,
    this.possibleValues,
  });

  dynamic value;
  String jsonKey;
  List<ValueElement> possibleValues;

  factory Field.fromJson(Map<String, dynamic> json) => Field(
    value: json["value"],
    jsonKey: json["jsonKey"],
    possibleValues: json["possibleValues"] == null ? null : List<ValueElement>.from(json["possibleValues"].map((x) => ValueElement.fromJson(x))),
  );

  Map<String, dynamic> toJson() => {
    "value": value,
    "jsonKey": jsonKey,
    "possibleValues": possibleValues == null ? null : List<dynamic>.from(possibleValues.map((x) => x.toJson())),
  };
}

class ValueElement {
  ValueElement({
    this.id,
    this.weight,
  });

  String id;
  double weight;

  factory ValueElement.fromJson(Map<String, dynamic> json) => ValueElement(
    id: json["id"],
    weight: json["weight"].toDouble(),
  );

  Map<String, dynamic> toJson() => {
    "id": id,
    "weight": weight,
  };
}
