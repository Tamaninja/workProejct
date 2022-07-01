import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:frontend/TamaForm.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  static const String _title = 'Flutter Code Sample';

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: _title,
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);
  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text("test")),
        floatingActionButton: FutureBuilder<dynamic>(
            future: fetchOptions(),
            builder:
                (BuildContext context, AsyncSnapshot<dynamic> snapshot) {
              if (snapshot == null) {
                return (const Center(child: CircularProgressIndicator(
                )));
              } else {

                return (FloatingActionButton(
                    child: Icon(Icons.add),
                    onPressed: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => FormWidget(snapshot.requireData)
                      ));
                }));
              }
            }));
  }

  Future<List<List<TamaCard>>> fetchOptions() async {
    Dio dio = new Dio();
    Response response = await dio.get("http://localhost:8080/test");
    PalletForm palletForm =  PalletForm.fromJson(response.data);
    List<List<TamaCard>> cards = <List<TamaCard>>[];
    cards.add(palletForm.palletContent!);
    cards.add(palletForm.palletContainer!);
    cards.add(palletForm.palletType!);
    // List<List<TamaCard>> tamaCards = [];
    // data.forEach((jsonModel) {
    //   print(jsonModel);
    //   List<TamaCard> cardList = [];
    //   jsonModel.forEach((x) {
    //     TamaCard tamaCard = TamaCard.fromJson(x);
    //     cardList.add(tamaCard);
    //   });
    //   tamaCards.add(cardList);
    // });
    return (cards);
  }
}

class FormWidget extends StatefulWidget {
  List<dynamic> cards;
  FormWidget(this.cards, {Key? key}) : super(key: key);
  @override
  State<FormWidget> createState() => _FormWidgerState();

}

class _FormWidgerState extends State<FormWidget> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text(''),
        ),
        body: _buildListView(widget.cards));
  }

  Card listCard(var data) {
    String title = data.runtimeType.toString();
    String subtitle = data.toString();
    if (data is TamaCard) {
      title = data.title!;
      subtitle = data.subtitle!;
    }

    return Card(
        child: ListTile(
            title: Center(
              child: Text(title),
            ),
            subtitle: Center(
              child: Text(subtitle),
            ),
            onTap: () {
              if (data is List<dynamic>) {
                Navigator.push(context,
                    MaterialPageRoute(builder: (_) => FormWidget(data)));
              } else {
                Navigator.pop(context, true);
              }
            }));
  }

  ListView _buildListView(var cards) {
    int length;
    List<dynamic> cardList = <dynamic>[];
    if (cards is List) {
      cardList = cards;
    }
    if (cards is PalletForm) {
      cardList.add(cards.palletType!);
      cardList.add(cards.palletContainer!);
      cardList.add(cards.palletContent!);
    }
    return (ListView.builder(
        shrinkWrap: true,
        itemCount: cardList.length,
        itemBuilder: (_, index) {
          return (listCard(cardList[index]));
        }));
  }
}
