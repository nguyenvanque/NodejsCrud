const mongoose = require("mongoose");

var Catelory = require("../models/Categories");

exports.insert = function (req, res) {
  let products = new Catelory({
    name: req.body._name,
  });
  products.save(function (err) {
    if (err) {
      return next(err);
    }
    exports.getAll(req,res);
  });
};

exports.delete = (req, res) => {
  let id = req.body.del_ID;
  console.log(id);
  Catelory.findByIdAndRemove({ _id: id },
    (err) => {
      if (err) {
        console.log(err);
      }
      res.redirect('Categories')
    });
};


exports.update = (req, res) =>{
  let id = req.body.edit_ID;
  let _name = req.body.edit_Name;
  Catelory.findByIdAndUpdate(
    { _id: id },
    { name: _name },
    function(err, result) {
      if (err) {
        console.log(err);
      }
      exports.getAll(req,res);
    });
};

exports.getAll = async function (req, res) {
  await Catelory.find({}).lean().exec((err, data) => {
    res.render("Categories", { cateloryList: data });
  });
};


