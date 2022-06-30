
import 'package:flutter/material.dart';
import 'package:dio/dio.dart';


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
        title: const Text('Tmarim test'),
      ),


      floatingActionButton: FloatingActionButton(
          child: const Icon(Icons.add),
          onPressed: () {
            final List<String> values = <String>['1', '2', '3', '4'];
            final List<String> titles = <String>['Pallet type', 'Pallet weight', 'Amount', 'Content'];
            Navigator.push(context,
                MaterialPageRoute(
                    builder:(_) => DynamicList(titles, values, false)
                )
          );
        }
      )
    );
  }
}


class DynamicList extends StatefulWidget {
  final List<String> entries;
  final List<String> titles;
  bool returnOnChoose;

  DynamicList(this.entries,this.titles,this.returnOnChoose, {Key? key}) : super(key: key);

  @override
  State<DynamicList> createState() => _DynamicListState();
}

class _DynamicListState extends State<DynamicList> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Tmarim test'),
      ),
      body: _buildListView(false),
    );
  }


  ListView _buildListView(bool returnOnChoose) {
    return (
        ListView.builder(
          shrinkWrap: true,
            itemCount: widget.entries.length,
            itemBuilder: (_, index) {
              return Card(
                child: ListTile(
                  title: Center(
                    child: Text(widget.entries[index]),
                  ),
                  subtitle: Center(
                    child: Text(widget.titles[index]),
                  ),
                  onTap: () {
                    if (widget.returnOnChoose){
                      Navigator.pop(context, true);
                      return;
                    }
                    Navigator.push(context, MaterialPageRoute(builder:(_) => DynamicList(getTitlesById(), getValuesById(), widget.returnOnChoose)));
                    widget.returnOnChoose = true;
                  }
                )
              );
            }
        )
    );
  }

  void fetchAlbum() async {
    try {
      Dio test = new Dio();
      String url = "http://localhost:8080/pallet/test";
      var response = await Dio().get(url);
      print(response);
    } catch (e) {
      print(e);
    }
  }

  List<String> getTitlesById() {
    try {
      fetchAlbum();
    }
    finally {
      return (<String>['1', '2', '3', '4']);
    }
  }
  List<String> getValuesById() {
    return (<String>['1', '2', '3', '4']);
  }
}

