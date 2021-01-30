const express=require('express');

const router=express.Router();

const API=require('../controllers/API')


//getAll
router.get("/api/products",API.getAll);

router.get("/api/product:id",API.getProductById);

router.get("/api/product/edit/:id",API.editProduct);

router.get("/api/product/delete/:id",API.deleteProduct);

module.exports=router;