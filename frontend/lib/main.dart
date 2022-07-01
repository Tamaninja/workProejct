
import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';


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
      appBar: AppBar(
        title:const Text("test")
      ),
      floatingActionButton: FloatingActionButton(
          child: const Icon(Icons.add),
          onPressed: () {
            Navigator.push(context,
                MaterialPageRoute(
                    builder:(_) => DynamicList(fetchOptions())
                )
          );
        }
      )
    );
  }
  Future<List<Options>> fetchOptions() async{
    Dio dio = new Dio();
    Response response = await dio.get("http://localhost:8080/test");
    final data = response.data;
    List<Options> options = [];
    data.forEach((jsonModel) {
      options.add(Options.fromJson(jsonModel));
    });
    return(options);
  }
}


class DynamicList extends StatefulWidget {
  Future<List<Options>> options;
  DynamicList(this.options, {Key? key}) : super(key: key);

  @override
  State<DynamicList> createState() => _DynamicListState();
}

class _DynamicListState extends State<DynamicList> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(''),
      ),
      body: FutureBuilder<List<Options>>(
        future: widget.options,
        builder: (BuildContext context, AsyncSnapshot<List<Options>> snapshot) {
          if(snapshot.data == null) {
            return (const Text("error"));
          } else {
            return(_buildListView(snapshot.data as List<Options>));
          }
        },
      ),
    );
  }



  Card listCard(Options options) {
    return Card(
        child: ListTile(
            title: Center(
              child: Text(options.identifier),
            ),
            subtitle: Center(
              child: Text(options.weight.toString()),
            ),
            onTap: () {
              Navigator.pop(context, true);
            }
        )
    );
  }

  ListView _buildListView(List<Options> options) {
    return (
        ListView.builder(
            shrinkWrap: true,
            itemCount: options.length,
            itemBuilder: (_, index) {
              return(listCard(options[index]));
            }
        )
    );
  }
}
class Options {
  String identifier;
  double weight;

  Options({
    required this.identifier,
    required this.weight,
  });
  factory Options.fromJson(Map<String, dynamic> json) => Options(
    identifier: json["identifier"],
    weight: json["weight"].toDouble(),
  );

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['identifier'] = this.identifier;
    data['weight'] = this.weight;
    return data;
  }
}
