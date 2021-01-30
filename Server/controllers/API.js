const Product = require('../models/product.model')


exports.getAll = async function (req, res) {
    try {
        let products = await Product.find({});
        res.send(products);
    } catch (error) {
        console.log(error);
    }
};

exports.getProductById=async (request,respon)=>{

}
exports.editProduct=async (request,respon)=>{
    
}
exports.deleteProduct=async (request,respon)=>{
    
}