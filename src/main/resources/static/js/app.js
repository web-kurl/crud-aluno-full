angular.module("app", [])
	.controller("alunoController", function ($scope, $http) {
	    $scope.alunos = [];
	    $scope.aluno = {};
	    $scope.tab = 1;
	    
	    $scope.carregarAluno = function(){
	    	$http({	method: 'GET',url: 'https://app-crudaluno.herokuapp.com/alunos'})
	    	.then(function (response) {
					$scope.alunos = response.data;
					console.log(response.data);
			}, function(response) {
					console.log("Falha ao buscar dados no Banco")
			});
	    };
	    
	    $scope.carregarAluno();
	    
	    $scope.btnSalvar = function(aluno){
	    	if(aluno.id > 0){
				$http({method:'POST', url:'https://app-crudaluno.herokuapp.com/alunos', data:angular.copy(aluno)})
				.then (
				function successCallBack(response){
					$scope.alunos.push(aluno);
			        $scope.aluno.dataMatricula = null;  
			        $scope.aluno.selecionado = false;
			        delete $scope.aluno;
			        $scope.tab = 2;						
				}
				,function errorCallBack(response){
					console.log(response.status);
					console.log(response.data);					
				});
	    	} else {
	    		$http({method:'PUT', url:'https://app-crudaluno.herokuapp.com/alunos', data:angular.copy(aluno)})
				.then (
				function successCallBack(response){
					for (var i = 0; i<$scope.alunos.length; i++){
						if($scope.alunos[i].id == aluno.id)
							$scope.alunos[i] = aluno; 
					}												
			        $scope.aluno.dataMatricula = null;  
			        $scope.aluno.selecionado = false;
			        delete $scope.aluno;
			        $scope.tab = 2;						
				}
				,function errorCallBack(response){
					console.log(response.status);
					console.log(response.data);					
				});
	    		
	    	}
		};
		
		$scope.btnDeletar = function(alunos){			
			$scope.count = 0;
	    	$scope.alunos = alunos.filter(function(aluno){
	    		if (aluno.selecionado) {
	    			$http({method:'DELETE', url:'https://app-crudaluno.herokuapp.com/alunos/' + aluno.id})
	    			.then (
	    			function successCallBack(response){
	    				console.log('Aluno com id=' + aluno.id + ' e nome de ' + aluno.nome + ' removido com sucesso do banco de dados');
	    			}
	    			,function errorCallBack(response){
						console.log(response.status);
						console.log(response.data);	    				
	    			});	    				    				    			       
	    		}else
	    			return aluno;
	    	});	           
	    };
	    
	    $scope.btnLimpar= function(){              
	        $scope.aluno.dataMatricula = null;  
	        delete $scope.aluno;	       
	    };
	    
	    $scope.doubleClick = function (aluno){
	    	$scope.aluno = aluno;
	    	$scope.tab = 1;
	    };
});