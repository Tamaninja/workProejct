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
              {
                return (FloatingActionButton(
                    child: const Icon(Icons.add),
                    onPressed: () {
                      Navigator.push(
                              context,
                              PageRouteBuilder(
                                  pageBuilder: (_, __, ___) =>
                                      FormWidget()));
                    }));
              }
            }));
  }

  Future<TamaForm> fetchOptions() async {
    Dio dio = Dio();
    Response response = await dio.get("http://localhost:8080/test");
    var data = response.data;
    TamaForm tamaForm = TamaForm.fromJson(data);
    return (tamaForm);
  }
}

class FormWidget extends StatefulWidget {
  FormWidget({Key? key}) : super(key: key);
  @override
  State<FormWidget> createState() => _FormWidgetState();
}

class _FormWidgetState extends State<FormWidget> {
  bool test = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("form test"),
      ),
      body: Form(
        child: Center(
          child: Card(
            child: Switch(
              value: test,
              onChanged: (bool newValue) {
                // This is called when the user toggles the switch.
                setState(() {
                  test = newValue;
                });
              },)
          ),
          )
        ),
      );
  }
}
