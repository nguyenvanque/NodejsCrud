const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let CatelorySchema = new Schema({
    name: String,
});
module.exports = mongoose.model('Categories', CatelorySchema);