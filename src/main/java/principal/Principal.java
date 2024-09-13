/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package principal;

import datos.*;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        ArtistaJDBC artistaJDBC = new ArtistaJDBC();    //Instancia de la clase ArtistaJDBC.
        Artista artista = new Artista();    //Instancia de la clase Artista.
        
        AlbumJDBC albumJDBC = new AlbumJDBC();  //Instancia de la clase AlbumJDBC.
        Album album = new Album();  //Instancia de la clase Album.
        
        CancionJDBC cancionJDBC = new CancionJDBC();    //Instancia de la clase CancionJDBC.
        Cancion cancion = new Cancion();    //Instancia de la clase Cancion.
        
        GeneroJDBC generoJDBC = new GeneroJDBC();   //Instancia de la clase GeneroJDBC.
        List <Genero> generos = generoJDBC.select();    //Lista para leer la tabla Genero de la BD.
        
        Scanner teclado = new Scanner(System.in);   //Objeto tipo sccaner para ingresar datos desde el teclado.
        int menu = 0;   //Variable para ingresar una opcion del menu principal.
        do{
            System.out.println("\n\t+----------------------------+");
            System.out.println("\t|     BIBLIOTECA MUSICAL     |");
            System.out.println("\t+---+------------------------+");
            System.out.println("\t| 1 |    Ingreso de Datos    |");
            System.out.println("\t| 2 |   Consulta de  Datos   |");
            System.out.println("\t| 3 |    Actualizar datos    |");
            System.out.println("\t| 4 |     Eliminar datos     |");
            System.out.println("\t| 5 |          Salir         |");
            System.out.println("\t+---+------------------------+");
            System.out.println("\nIngrese la opcion que desea acceder: ");
            menu = validarInt();
            switch(menu){
                //Ingreso de Datos - Crear dato en la DB - Create 
                case 1:
                    int opcion1=0;  //Variable para ingresar una opción.
                    boolean encotrado1=true;    //Variable para condicional.
                    do{ 
                        System.out.println("\n\t+-----------------------+");
                        System.out.println("\t|    INGRESO DE DATOS   |");
                        System.out.println("\t+---+-------------------+");
                        System.out.println("\t| 1 |   Nuevo Artista   |");
                        System.out.println("\t| 2 |    Nuevo Album    |");
                        System.out.println("\t| 3 |   Nueva Cancion   |");
                        System.out.println("\t| 4 |       Salir       |");
                        System.out.println("\t+---+-------------------+");
                        System.out.println("\nIngrese la opcion que desea acceder: ");
                        opcion1 = validarInt();
                        switch(opcion1){
                        case 1:
                            System.out.println("\n\tNUEVO ARTISTA");
                            do{
                                System.out.println("Ingreso de datos\nID del artista:");
                                artista.setIdArtista(validarInt());
                                if(artistaJDBC.selectCondicion(artista.getIdArtista())==1){
                                    System.out.println("\tError: Artista resgistrado en la BD\n");
                                    encotrado1 = true;
                                }else{
                                    System.out.println("Nombre del artista: ");
                                    artista.setNombreArtista(teclado.nextLine());
                                    artistaJDBC.insert(artista);
                                    encotrado1=false;
                                }
                            }while(encotrado1==true);
                            break;
                            
                        case 2:
                            System.out.println("\n\tNUEVO ALBUM");
                            do{
                                System.out.println("Ingreso de datos\nID del album: ");
                                album.setIdAlbum(validarInt());
                                if(albumJDBC.selectCondicion(album.getIdAlbum())==1){
                                    System.out.println("\tError: Album resgistrado en la BD\n");
                                    encotrado1 = true;
                                }else{
                                    System.out.println("Titulo del album: ");
                                    album.setNombre(teclado.nextLine());
                                    while(artistaJDBC.selectCondicion(album.getIdArtista())!=1){    /*Condicional para validar que el ID del artista este registrado en BD*/
                                        System.out.println("ID del artista: ");
                                        album.setIdArtista(validarInt());
                                        if(artistaJDBC.selectCondicion(album.getIdArtista())!=1){
                                            System.out.println("Error: Artista no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    System.out.println("Año de produccion:");
                                    album.setAnio(teclado.nextLine());
                                    albumJDBC.insert(album);
                                    encotrado1=false;
                                }
                            }while(encotrado1==true);
                            break;
                            
                        case 3:
                            System.out.println("\n\tNUEVA CANCION");
                            do{
                                System.out.println("Ingrese de datos\nID de la cancion: ");
                                cancion.setIdCancion(validarInt());
                                if(cancionJDBC.selectCondicion(cancion.getIdCancion())==1){
                                    System.out.println("\tError: Cancion resgistrada en la BD\n");
                                    encotrado1 = true;
                                }else{
                                    System.out.println("Nombre de la cancion: ");
                                    cancion.setNombre(teclado.nextLine());
                                    while(artistaJDBC.selectCondicion(cancion.getIdArtista())!=1){    /*Condicional para validar que el ID del artista este registrado en BD*/
                                        System.out.println("ID del artista: ");
                                        cancion.setIdArtista(validarInt());
                                        if(artistaJDBC.selectCondicion(cancion.getIdArtista())!=1){
                                            System.out.println("Error: Artista no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    while(albumJDBC.selectCondicion(cancion.getIdAlbum())!=1){    /*Condicional para validar que el ID del album este registrado en BD*/
                                        System.out.println("ID del album: ");
                                        cancion.setIdAlbum(validarInt());
                                        if(albumJDBC.selectCondicion(cancion.getIdAlbum())!=1){
                                            System.out.println("Error: Artista no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    System.out.println("\tGeneros");
                                    //pausa
                                    System.out.println("+--------+--------------------------+");
                                    generos.forEach(genero->{   /*Leer de la BD la tabla genero - Read*/
                                        genero.imprimirInfo();
                                    });
                                    System.out.println("+--------+--------------------------+");
                                    while(generoJDBC.selectCondicion(cancion.getIdGenero())!=1){    /*Condicional para validar que el ID del album este registrado en BD*/
                                        System.out.println("ID del genero: ");
                                        cancion.setIdGenero(validarInt());
                                        if(generoJDBC.selectCondicion(cancion.getIdGenero())!=1){
                                            System.out.println("Error: Genero no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    System.out.println("Descripcion de la cancion: ");
                                    cancion.setDescripcion(teclado.nextLine());
                                    cancionJDBC.insert(cancion);
                                    encotrado1=false;
                                }  
                            }while(encotrado1==true);
                            break;
                            
                        case 4:
                            break;
                        default:
                            System.out.println(" Error: No ingreso una opcion valida.");
                        }   
                    }while(opcion1!=4);
                    break;
                    
                // Mostrar Datos - Leer de la BD - Read
                case 2:
                    int opcion2=0;
                    boolean encotrado2 = true;
                    do{
                        System.out.println("\n\t+------------------------+");
                        System.out.println("\t|    CONSULTA DE DATOS   |");
                        System.out.println("\t+---+--------------------+");
                        System.out.println("\t| 1 |   MOSTRAR TABLAS   |");
                        System.out.println("\t| 2 |      BUSQUEDA      |");
                        System.out.println("\t| 3 |       Salir        |");
                        System.out.println("\t+---+--------------------+");
                        System.out.println("\nIngrese la opcion que desea acceder: ");
                        opcion2 = validarInt();
                        switch(opcion2){
                            case 1:
                                System.out.println("\n\tTABLAS");
                                 //Muestra datos de la tabla artista
                                List <Artista> artistas = artistaJDBC.select();
                                System.out.println("Artistas");
                                artistas.forEach(artista1->{
                                    System.out.println(artista1);
                                });
                                System.out.println("");
                                
                                //Muestra datos de la tabla album con relacion a tabla artista
                                System.out.println("\tAlbunes");
                                List <Album> albunes = albumJDBC.select();
                                albunes.forEach(album1->{
                                    album1.imprimirRelacion();
                                });
                                System.out.println("");
                                
                                 //Muestra datos de la tabla cancion con su realcion a otras tablas
                                System.out.println("\tCanciones");
                                cancionJDBC.selectInnerJoin();
                                
                                break;
                                
                            case 2:
                                System.out.println("\n\tBUSQUEDA");
                                System.out.println("Ingrese el mombre del artista, album o cancion  que desea buscar: ");
                                String busqueda = teclado.nextLine();
                                System.out.println("Artistas encotrados: ");
                                artistaJDBC.selectLike(busqueda);
                                System.out.println("\nAlbunes encotrados: ");
                                albumJDBC.selectLike(busqueda);
                                System.out.println("\nCanciones encotrados: ");
                                cancionJDBC.selectLike(busqueda);
                                break;
                                
                            case 3:
                                break;
                            default:
                                System.out.println(" Error: No ingreso una opcion valida.");
                        }
                    }while(opcion2!=3);
                    break;
                    
                // Actualizar Datos - Actualizar dato en la BD - Update
                case 3:
                    int opcion3=0;
                    do{
                        System.out.println("\n\t+----------------------------+");
                        System.out.println("\t|      ACTUALIZAR DATOS      |");
                        System.out.println("\t+---+ -----------------------+");
                        System.out.println("\t| 1 |   Actualizar Artista   |");
                        System.out.println("\t| 2 |    Actualizar Album    |");
                        System.out.println("\t| 3 |   Actualizar Cancion   |");
                        System.out.println("\t| 4 |          Salir         |");
                        System.out.println("\t+---+------- ----------------+");
                        System.out.println("\nIngrese la opcion que desea acceder: ");
                        opcion3 = validarInt();
                        switch(opcion3){
                            case 1:
                                System.out.println("\n\tACTUALIZAR ARTISTA");
                                System.out.println("Ingrese ID del artista para actualizar: "); 
                                artista.setIdArtista(validarInt());
                                if(artistaJDBC.selectCondicion(artista.getIdArtista())==1){
                                    System.out.println("Nuevo nombre del artista: ");
                                    artista.setNombreArtista(teclado.nextLine());
                                    artistaJDBC.update(artista);
                                }else{
                                    System.out.println("\tArtista no encontrado\n\tVerifique su ingreso\n");
                                }
                                break;
                                
                            case 2:
                                System.out.println("\n\tACTUALIZAR ALBUM");
                                System.out.println("Ingrese ID del album para actualizar: "); 
                                album.setIdAlbum(validarInt());
                                if(albumJDBC.selectCondicion(album.getIdAlbum())==1){
                                    System.out.println("Nuevo titulo del album: ");
                                    album.setNombre(teclado.nextLine());
                                    while(artistaJDBC.selectCondicion(album.getIdArtista())!=1){    /*Condicional para validar que el ID del artista este registrado en BD*/
                                        System.out.println("Nuevo ID del artista: ");
                                        album.setIdArtista(validarInt());
                                        if(artistaJDBC.selectCondicion(album.getIdArtista())!=1){
                                            System.out.println("Error: Artista no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    System.out.println("Nueva fecha de lanzamiento: ");
                                    album.setAnio(teclado.nextLine());
                                    albumJDBC.update(album);
                                }else{
                                    System.out.println("\tArtista no encontrado\n\tVerifique su ingreso\n");
                                }
                                break;
                                
                            case 3:
                                System.out.println("\n\tACTUALIZAR CANCION");
                                System.out.println("Ingrese ID de la cancion para actualizar: "); 
                                cancion.setIdCancion(validarInt());
                                if(cancionJDBC.selectCondicion(cancion.getIdCancion())==1){
                                    System.out.println("Nuevo nombre de la cancion: ");
                                    cancion.setNombre(teclado.nextLine());
                                    while(artistaJDBC.selectCondicion(cancion.getIdArtista())!=1){    /*Condicional para validar que el ID del artista este registrado en BD*/
                                        System.out.println("Nuevo ID del artista: ");
                                        cancion.setIdArtista(validarInt());
                                        if(artistaJDBC.selectCondicion(cancion.getIdArtista())!=1){
                                            System.out.println("Error: Artista no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    while(albumJDBC.selectCondicion(cancion.getIdAlbum())!=1){    /*Condicional para validar que el ID del album este registrado en BD*/
                                        System.out.println("Nuevo ID del album: ");
                                        cancion.setIdAlbum(validarInt());
                                        if(albumJDBC.selectCondicion(cancion.getIdAlbum())!=1){
                                            System.out.println("Error: Artista no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    System.out.println("\tGeneros");
                                    //pausa
                                    System.out.println("+--------+--------------------------+");
                                    generos.forEach(genero->{   /*Leer de la BD la tabla genero - Read*/
                                        genero.imprimirInfo();
                                    });
                                    System.out.println("+--------+--------------------------+");
                                    while(generoJDBC.selectCondicion(cancion.getIdGenero())!=1){    /*Condicional para validar que el ID del album este registrado en BD*/
                                        System.out.println("Nuevo ID del genero: ");
                                        cancion.setIdGenero(validarInt());
                                        if(generoJDBC.selectCondicion(cancion.getIdGenero())!=1){
                                            System.out.println("Error: Genero no encontrado en la BD.\nIngrese de nuevo");
                                        }else{}
                                    }
                                    System.out.println("Nueva descripcion: ");
                                    cancion.setDescripcion(teclado.nextLine());
                                    cancionJDBC.update(cancion);
                                }else{
                                    System.out.println("\tArtista no encontrado\n\tVerifique su ingreso\n");
                                }
                                break;
                                
                            case 4:
                                break;
                            default:
                                System.out.println(" Error: No ingreso una opcion valida.");
                        }
                    }while(opcion3!=4);
                    break;
                    
                // Eliminar Datos - Eliminar dato de la BD - Delete
                case 4:
                    int opcion4=0;
                    do{
                        System.out.println("\n\t+--------------------------+");
                        System.out.println("\t|      ELIMINAR DATOS      |");
                        System.out.println("\t+---+----------------------+");
                        System.out.println("\t| 1 |   Eliminar Artista   |");
                        System.out.println("\t| 2 |    Eliminar Album    |");
                        System.out.println("\t| 3 |   Eliminar Cancion   |");
                        System.out.println("\t| 4 |        Salir         |");
                        System.out.println("\t+---+----------------------+");
                        System.out.println("\nIngrese la opcion que desea acceder: ");
                        opcion4 = validarInt();
                        switch(opcion4){
                            case 1:
                                System.out.println("\n\tELIMINAR ARTISTA");
                                System.out.println("Ingrese ID del artista para eliminar: ");
                                artista.setIdArtista(validarInt());
                                if(artistaJDBC.selectCondicion(artista.getIdArtista())==1){
                                    artistaJDBC.delete(artista);
                                }else{
                                    System.out.println("\tArtista no encontrado\n\tVerifique su ingreso\n");
                                }
                                break;
                                
                            case 2:
                                System.out.println("\n\tELIMINAR ALBUM");
                                System.out.println("Ingrese ID del album para eliminar: ");
                                album.setIdAlbum(validarInt());
                                if(albumJDBC.selectCondicion(album.getIdAlbum())==1){
                                    albumJDBC.delete(album);
                                }else{
                                    System.out.println("\tAlbum no encontrado\n\tVerifique su ingreso\n");
                                }
                                break;
                                
                            case 3:
                                System.out.println("\n\tELIMINAR CANCION");
                                System.out.println("Ingrese ID de la cancion para eliminar: ");
                                cancion.setIdCancion(validarInt());
                                if(cancionJDBC.selectCondicion(cancion.getIdCancion())==1){
                                    cancionJDBC.delete(cancion);
                                }else{
                                    System.out.println("\tCancion no encontrada\n\tVerifique su ingreso\n");
                                }
                                break;
                                
                            case 4:
                                break;
                            default:
                                System.out.println(" Error: No ingreso una opcion valida.");
                        }  
                    }while(opcion4!=4);
                    break;
                    
                // SALIR    
                case 5:
                    System.out.println("");
                    break;   
                default:
                    System.out.println(" Error: No ingreso una opcion valida.");
            }   
        }while(menu != 5);
    }
    
    public static int validarInt(){      /*Metodo para continuar por posibles valores inesperados en variables Int*/
        Scanner teclado = new Scanner(System.in); // Objeto para leer desde el teclado
         do{            /*Inicia ciclo*/
             if(teclado.hasNext()){     /*Condicional para verificar si la varible tiene un dato guardado.*/
                 if(teclado.hasNextInt()){      /*Condicional para verificar si la varible tiene guadado un dato Int*/
                    return teclado.nextInt();   //Retorna el dato Int guardado
                }
                 else{      /*Contrario segundo condicional*/
                    teclado.next();
                    System.out.println("\tERROR: No ingreso un entero.\n\tSolo se puede ingresar enteros."
                            + "\nIngrese de nuevo:");
                }
             }
             else{          /*Contrario primer condicional*/
                 return 0;
             }
        }while(true);   /*Fin del ciclo*/
    }

}
