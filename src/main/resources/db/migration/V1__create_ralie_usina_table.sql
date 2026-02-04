CREATE TABLE public.ralie_usina (
    id BIGSERIAL PRIMARY KEY,
    codigo_usina BIGINT NOT NULL,
    nome_usina VARCHAR(255) NOT NULL,
    uf VARCHAR(2),
    tipo_geracao VARCHAR(100),
    potencia_outorgada_kw NUMERIC(18,4),
    data_publicacao DATE,
    data_prevista_operacao DATE,
    data_realizada_operacao DATE,
    data_ingestao TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_codigo_data UNIQUE (codigo_usina, data_publicacao)
);