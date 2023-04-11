import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import { VerUnaNoticiaComponent } from './componente/ver-una-noticia/ver-una-noticia.component';
import { VistainicioComponent } from './componente/vistainicio/vistainicio.component';
import { ListarComponent } from './compontente/listar/listar.component';
import { NoticiaFormComponent } from './componente/noticia-form/noticia-form.component';
import { RegistroComponent } from './componente/seguridad/registro.component';
import { LogginComponent } from './componente/seguridad/loggin.component';
import { GuardianGuard } from './guardian.guard';

const routes: Routes = [
  {path:"noticia",component:VistainicioComponent},
  {path:"verNoticia/:id",component:VerUnaNoticiaComponent},
  {path:"listar",component:ListarComponent, canActivate:[GuardianGuard]},
  {path:'noticia/form',component:NoticiaFormComponent,canActivate:[GuardianGuard]},
  {path:'noticia/form/:id',component:NoticiaFormComponent,canActivate:[GuardianGuard]},
  {path:'usuario/form/:id',component:RegistroComponent,canActivate:[GuardianGuard]},
  {path:'usuario/form',component:RegistroComponent},
  {path:'login',component:LogginComponent},

]
@NgModule({
  imports: [RouterModule.forRoot(routes)],

exports: [RouterModule]
})
export class AppRoutingModule { }
