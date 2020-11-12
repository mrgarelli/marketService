#!/usr/bin/env python3

import sys, os
from declarecli import DeclarativeCLI, DeclarativeOptions, DeclarativeCommands
from syspy import Shell
sh = Shell()
import requests

baseuri = 'http://localhost:8080'

def postAddStudent(rollNumber, name):
    endpoint = baseuri + '/add'
    data = {'name': name, 'rollNumber': rollNumber}
    res = requests.post(url=endpoint, json=data)
    print(res.status_code)

def putAddGroceryItem(search_param, value):
    endpoint = baseuri + '/addGroceryItem'
    data = {search_param: value}
    res = requests.put(url=endpoint, json=data)
    print(res.status_code)
    print(res.json())
    print(res.reason)

version = 'Version: 0.0.1'

synopsis = '''\
Usage: integrate <--option|-o> <command>
tools for testing app api'''

class dir:
    home = os.path.expanduser('~')

class CLI(DeclarativeCLI):
    __level__ = 0
    class Synopsis:
        @staticmethod
        def body(): print(synopsis)

    class Options(DeclarativeOptions):
        class help_h:
            description = 'extended help message of this package'
            @staticmethod
            def instructions():
                cli.extended_help()
                sh.log.success()
        class verbose_v:
            description = 'get more descriptive command output'
            @staticmethod
            def instructions():
                sh.verbose = True
        class version:
            description = 'output the current tool version'
            @staticmethod
            def instructions():
                print(version)
                sh.log.success()

    class Commands(DeclarativeCommands):
        class addGrocery:
            description = '<qrUrl|id> <value> change a current students name'
            @staticmethod
            def instructions(remainder):
                if len(remainder) != 2:
                    sh.log.error('expected 2 arguments <qrUrl|id> <value>')
                search_param, value = remainder
                putAddGroceryItem(search_param, value)

        def __default_no_args__(self):
            cli.help()

cli = CLI()
cli.run(sys.argv[1:])
sh.finish()

