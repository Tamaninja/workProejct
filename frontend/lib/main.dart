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
        floatingActionButton: FutureBuilder<TamaForm>(
            future: fetchOptions(),
            builder: (BuildContext context, AsyncSnapshot<TamaForm> snapshot) {
              if (snapshot == null) {
                return (const Center(child: CircularProgressIndicator()));
              } else {
                return (FloatingActionButton(
                  heroTag: "newForm",
                    child: Icon(Icons.add),
                    onPressed: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) =>
                                  FormWidget(snapshot.requireData)));
                    }));
              }
            }));
  }

  Future<TamaForm> fetchOptions() async {
    Dio dio = Dio();
    Response response = await dio.get("http://localhost:8080/test");
    var data = response.data;
    TamaForm tamaForm = TamaForm("12345");
    data.forEach((jsonModel) {
      TamaList tamaCard = TamaList.fromJson(jsonModel);
      tamaForm.list.add(tamaCard);
    });
    return (tamaForm);
  }
}

class FormWidget extends StatefulWidget {
  TamaForm tamaForm;
  FormWidget(this.tamaForm, {Key? key}) : super(key: key);
  @override
  State<FormWidget> createState() => _FormWidgetState();
}

class _FormWidgetState extends State<FormWidget> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("form test"),
      ),
      body: Center(
        child: buildList(widget.tamaForm.list),
      ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {  },
          heroTag: "newForm",
          child: const Icon(Icons.send),
      )
    );
  }

  ListView buildList(List<dynamic> list) {
    return (ListView.builder(
        itemCount: list.length,
        itemBuilder: (BuildContext context, int index) {
          return (buildListTile(list[index]));
        }));
  }


  dynamic buildListTile(var data) {
    if(data is TamaList) {
      return Hero(
          tag: data.key,
          child: buildTile(data));
    } else if (data is TamaCard){
      return(buildRadioTile(data));
    }
  }


  Card buildTile(TamaList tamaList) {
    return (Card(
      child: ListTile(
        title: Text(tamaList.key.toString()),
        subtitle: Text(tamaList.value.toString()),
        onTap: () {
          Navigator.push(
              context,
              PageRouteBuilder(
                  pageBuilder: (_, __, ___) => Hero(
                    tag: tamaList.key,
                    child: Scaffold(
                          appBar: AppBar(
                            title: Text(tamaList.key),
                          ),
                          body:buildList(tamaList.options),
                        ),
                  )));
        },
      ),
    ));
  }

  Card buildRadioTile(TamaCard tamaCard) {
    return (Card(
      child: RadioListTile(
        title: Text(tamaCard.title.toString()),
        subtitle: Text(tamaCard.subtitle.toString()),
        groupValue: tamaCard.parent.value,
        value: tamaCard.title,
        onChanged: (dynamic value) {
          setState(() {
            tamaCard.parent.value = value;
            Navigator.pop(context, true);
          });
        },
      ),
    ));
  }
}
