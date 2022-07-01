import 'package:flutter/gestures.dart';

class PalletForm {
  List<TamaCard>? palletContent;
  List<TamaCard>? palletContainer;
  List<TamaCard>? palletType;

  PalletForm({required this.palletContent, required this.palletContainer,required this.palletType});

  PalletForm.fromJson(Map<String, dynamic> json) {
    palletContent = List<TamaCard>.from(json["palletContent"].map((x) => TamaCard.fromJson(x)));
    palletContainer = List<TamaCard>.from(json["palletContainer"].map((x) => TamaCard.fromJson(x)));
    palletType = List<TamaCard>.from(json["palletType"].map((x) => TamaCard.fromJson(x)));
  }

  Map<String, dynamic> toJson() => {
    "palletContent": List<dynamic>.from(palletContent!.map((x) => x.toJson())),
    "palletContainer": List<dynamic>.from(palletContainer!.map((x) => x.toJson())),
    "palletType": List<dynamic>.from(palletType!.map((x) => x.toJson())),
  };
}

class TamaCard {
  TamaCard({
    this.title,
    this.subtitle,
  });

  String? title;
  String? subtitle;

  TamaCard.fromJson(Map<String, dynamic> json){
    title = json[json.keys.first].toString();
    subtitle = json[json.keys.last].toString();
  }

  Map<String, dynamic> toJson() => {
    "title": title,
    "subtitle": subtitle,
  };
}