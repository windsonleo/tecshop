package com.tecsoluction.tecshop.entidade;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.tecsoluction.tecshop.framework.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "EMPRESA")
public class Empresa extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 1L;


	@Column(name = "NOMEFANTASIA", nullable = false)
    private String nomefantasia;

    
    private String imagem;
    
    private String cnpj;
    
    private String inscricaoestadual;
    
    private String telefone;
        
    private String latlong;
    
    private String longlat;
    
    private String email;
    
    private Date dataabertura;
    
    private String banner;
    

    public Empresa() {

    }

    
    @Override
    public String toString() {
        return nomefantasia;
    }


}
