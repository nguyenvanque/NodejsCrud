const { response } = require('express');
const nongoose = require('mongoose');
const Product = require("../models/product.model");

var Catelory = require("../models/Categories");
exports.delete = (req, res) => {
  let id = req.body.del_ID;
  console.log(id);
  Product.findByIdAndRemove({ _id: id },
    (err) => {
      if (err) {
        console.log(err);
      }
      exports.getAlldata(req, res);
    });
};
exports.insert = function (req, res) {
  let cate = req.body._catelory;
  if(cate == "Select Catelory"){
    return;
  }
  let products = new Product({
    pro_Img: req.body.txtImg,
    catelory: cate,
    pro_Name: req.body.txtName,
    pro_Price: req.body.txtPrice,
  });
  products.save(function (err) {
    if (err) {
      return next(err);
    }
    exports.getAlldata(req, res);
  });
};


// exports.getAlldata = async function (req, res) {
//   await Product.find({})
//     .lean()
//     .exec((err, data) => {
//       res.render("Product", { productList: data });
//     });
// };
exports.getAlldata = async function (req, res) {
  await Product.find({})
    .lean()
    .exec((err, dataproduct) => {
      Catelory.find({}).lean().exec((err, data) => {
        res.render("Product", { productList: dataproduct, productCate: data });
      });
    });
};




exports.update = (req, res) => {

  let id = req.body.edit_ID;
  let _catelory = req.body.edit_catelory;
  let _img = req.body.edit_img;
  let _name = req.body.edit_name;
  let _price = req.body.edit_price;

  Product.findByIdAndUpdate(

    { _id: req.body.edit_ID }, { catelory:_catelory,pro_Img: _img, pro_Name: _name, pro_Price: _price }
    ,
    function (err, result) {
      if (err) {
        console.log(err);
      }
      exports.getAlldata(req, res);
    });
};
