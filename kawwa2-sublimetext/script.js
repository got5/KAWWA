
fs = require('fs');
var builder = require('xmlbuilder');


function endsWith(string, suffix) {
    return string.indexOf(suffix, string.length - suffix.length) !== -1;
};
function hasToBeASnippet(file){
  return (endsWith(file, "snippet.css") || endsWith(file, "snippet.js") || endsWith(file, ".scss") || endsWith(file, ".css") || endsWith(file, "snippet.html")) && 
    file.indexOf("01_pageStructure") === -1 && file.indexOf("templates_html5") === -1 && file.indexOf("dependencies") === -1
    && file.indexOf("/tapestry/") === -1 && file.indexOf("/xhtml/") === -1
}

function getDirectoryFiles(directory, callback) {
  fs.readdir(directory, function(err, files) {
    files.forEach(function(file){

      fs.stat(directory + '/' + file, function(err, stats) {
        if(stats.isFile() && hasToBeASnippet(directory + '/' + file)) {
          callback(file, directory + '/' + file);
        }
        if(stats.isDirectory()) {
          getDirectoryFiles(directory + '/' + file, callback);
        }
      });
    });
  });
}

getDirectoryFiles('../Portal/kawwa_components', function(file, file_with_path) {
  console.log(file_with_path);
  var nameTab = file_with_path.split('/');
  var name = nameTab[nameTab.length - 2].split("_").pop();

  var extension = file.split('.').pop();
  var scope = "source."+extension;
  if(extension === "html") scope = "text."+extension;
  if(extension === "css" || extension === "scss") {
    var theme =   
    name = name + "_" + file.split('.')[0];
  }

  fs.readFile(file_with_path, 'utf8', function (err, data) {
    if (err) throw err;
    createFile(data, name, scope, extension)
    
  });
  
});



function createFile(data, name, scope, extension){
  console.log(extension+'/'+name+'.sublime-snippet')
  var doc = builder.create();
    doc.begin('snippet')
    .ele('content')
      .cdata(replateChr(data, extension))
      .up()
    .ele('tabTrigger')
      .txt(name)
      .up()
    .ele('scope')
      .txt(scope);
    fs.writeFile(extension+'/'+name+'.sublime-snippet', doc.toString({ pretty: true }), function(err) {}); 
}
//With Sublime Snippet, the $ char is used. Problem if jQuery code use $
function replateChr(data, ext){
  //todo
  if(ext === "js"){
    return data.replace("$", "jQuery");  
  }
  return data;
}
