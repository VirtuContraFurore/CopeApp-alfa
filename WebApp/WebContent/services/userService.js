app.service('UserService', UserService);

function UserService($q) {

	this.login = function(mail, password) {
		return $q(function(resolve, reject) {
			if (mail != 'errore@gmail.com') {
				resolve({
					userId : '0',
					mail : 'a@a.it',
					firstname : 'Andrea',
					lastname : '',
					nickname : 'shish',
					classe : "4",
					sezione : "C",
					password : 'ungu',
					roles : [ 'studente', 'moderatore', 'admin' ],
					imageUrl : ''
				});
			} else {
				reject(null)
			}
		});
	}
}