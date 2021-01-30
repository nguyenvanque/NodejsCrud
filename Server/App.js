const express=require('express');
const handlebar=require('express-handlebars');
const routes=require('./Routs/Rout')
const api=require('./Routs/API')
const app=express();

app.listen(3000,()=>{
 console.log(`Server running at port 3000`);
})

app.engine(".hbs",
handlebar({
    defaultLayout:''
}));
app.set('view engine', '.hbs');
app.use(express.static('public'))
app.use('*/js',express.static('public/js'))
app.use('*/css',express.static('public'))

app.use(routes);
app.use(api);

const mongoose = require('mongoose');
const { request } = require('express');

let dev_db_url='mongodb+srv://sa:123@cluster0.yibf5.mongodb.net/Server_Android?retryWrites=true&w=majority';
mongoose.connect(
    dev_db_url,
    {useUnifiedTopology: true, useNewUrlParser: true},
    err=>{
      if(err){

        console.log('Can not connect mongodb, because '+err)
      }else{
        console.log('Connect to mongodb successful')
      }
    }
  )
  mongoose.Promise = global.Promise;
  const db = mongoose.connection;
  db.on('error',console.error.bind(console,'MongoDB connection error'));