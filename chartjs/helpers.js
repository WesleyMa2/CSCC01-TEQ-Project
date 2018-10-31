module.exports = {
    getJSON: function(str) {
        try {
            JSON.parse(str);
            return true;
        } catch (e) {
            console.log(e);
            return false;
        }
    }
  };