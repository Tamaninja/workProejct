
import 'package:flutter/material.dart';

void main() {
  print('test');
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
      home: MyStatefulWidget(),
    );
  }


}
class MyStatefulWidget extends StatefulWidget {
  const MyStatefulWidget({Key? key}) : super(key: key);
  @override
  State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
}

class _MyStatefulWidgetState extends State<MyStatefulWidget> {
  int _count = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Tmarim test'),
      ),
      body: Center(child: Text('You have pressed the button $_count times.')),
      floatingActionButton: FloatingActionButton(
        tooltip: 'Increment Counter',
        child: const Icon(Icons.add),
        onPressed: () {
          Navigator.push(context,
              MaterialPageRoute(
                builder:(_) => newPallet()));
        },
      )
    );
  }
}


class newPallet extends StatefulWidget {
  const newPallet({Key? key}) : super(key: key);

  @override
  State<newPallet> createState() => _newPalletState();
}

class _newPalletState extends State<newPallet> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
        ),
      body: Column(
        children: <Widget>[
          ListTile(
            title: const Text("test"),
          ),
          ListTile(
            title: const Text("data"),
          )
        ]
    ),
    );
  }
}
