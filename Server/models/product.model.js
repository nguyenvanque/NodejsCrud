const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let ProductSchema = new Schema({
    // pro_Name: {type: String,required:true,max:100},
    // pro_Img: {type:String,required:true},
    // pro_Price:{type:Number,required: true},

    pro_Img: String,
    catelory: String,
    pro_Name: String,
    pro_Price: String,
})

// Export the model
module.exports = mongoose.model('Product', ProductSchema);